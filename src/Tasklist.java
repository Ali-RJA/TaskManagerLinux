import java.io.*;
import java.util.ArrayList;

public class Tasklist {

     ArrayList<Task> taskList;

     /* This function reads the content from a file that obtain the list of process id's using bash, then formats
      * it into nice strings.
      * @Param process, the process that is associated with the command
      * @Return ArrayList<Task>, a dynamic array that contains (pid, name) records
     */
    public ArrayList<Task> getList() throws IOException {
        this.taskList = new ArrayList<>();
        FileInputStream fstream = new FileInputStream("C:\\Users\\alial\\IdeaProjects\\javagui\\src\\list.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        br.readLine();
        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            String[] splited = strLine.split("\\s+");
            taskList.add(new Task(Integer.parseInt(splited[1]),splited[2]));
        }
        //Close the input stream
        fstream.close();
        br.close();
        return taskList;
    }

    /* This function reads the standard output of a process that obtain the list of process id's using bash, then formats
     * it into nice strings.
     * @Param process, the process that is associated with the command
     * @Return ArrayList<Task>, a dynamic array that contains (pid, name) records
     */
    public ArrayList<Task> getListFromProcess(Process process) throws IOException {
        this.taskList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String strLine;
        br.readLine();
        //Read File Line By Line
        while ((strLine = br.readLine()) != null)   {
            String[] splited = strLine.split("\\s+");
            taskList.add(new Task(Integer.parseInt(splited[1]),splited[2]));
        }
        //Close the input stream
        br.close();
        return taskList;
    }
}
