import jdk.jshell.execution.Util;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JFrame.*;
import javax.swing.JScrollPane.*;

public class lab4 extends JFrame {
    //Creating the frame and making an utilities object
    JFrame f;
    Utilities utilities = new Utilities();

    /* This method is the crux of the entire project, it creates and 
    * displays the processes in a table and provides the option to terminate the selected the process.
    * @param This function doesn't take any parameter directly but does pull values from other files
    * @return This function doesn't return anything but displays everything in a new window 
    */
    public lab4() throws IOException {
        getContentPane().setLayout(null);
        f=new JFrame("Processes");
        f.setLayout(new BorderLayout());
        //Creating table with checkbox column
        JTable jt=new JTable();
        DefaultTableModel model = new DefaultTableModel(){
            public Class <?> getColumnClass(int column){
                switch(column){
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return String.class;
                }
            }
        };
        jt.setModel(model);
        model.addColumn("PID");
        model.addColumn("Process Name");
        model.addColumn("Terminate");
        //Getting list of all processes from utilities file
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = utilities.populateFile();
        for(int i = 0; i< tasks.size();i++){
            model.addRow(new Object[0]);
            model.setValueAt(tasks.get(i).pid, i, 0);
            model.setValueAt(tasks.get(i).taskName, i, 1);
            model.setValueAt(false, i, 2);
        }

        //Setting table dimensions
        jt.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(jt);
        getContentPane().add(sp);
        f.add(sp, BorderLayout.CENTER);
        f.setSize(500,500);

        //Creating terminate and refresh buttons
        JButton terminate = new JButton("Terminate");
        terminate.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<jt.getRowCount();i++){
                    Boolean check = Boolean.valueOf(jt.getValueAt(i,2).toString());
                    String pid = jt.getValueAt(i, 0).toString();

                    if(check){
                        try {

                            utilities.killTask(Integer.parseInt(pid));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                JOptionPane.showMessageDialog(null, "Selected Processes Terminated! Refresh for accuracy");
            }
        });

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
            int size = model.getRowCount();
            int sizeTest = 0; 
           

                for (int i = 0; i < size; i++) {
                model.removeRow(0);
                }
                ArrayList<Task> tasks = new ArrayList<>();
                try {
                    tasks = utilities.populateFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                for(int i = 0; i< tasks.size();i++){
                    model.addRow(new Object[0]);
                    model.setValueAt(tasks.get(i).pid, i, 0);
                    model.setValueAt(tasks.get(i).taskName, i, 1);
                    model.setValueAt(false, i, 2);
                    sizeTest++;
                }
            }
        });

        terminate.setBounds(100,350,100,30);
        f.add(refresh, BorderLayout.NORTH);
        f.add(terminate, BorderLayout.SOUTH);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /* This method just calls the constructor 
     * @param String args
     * @return This doesn't return anything
     */
    public static void main(String[] args) throws IOException {
        new lab4();
    }
}



