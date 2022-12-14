import java.io.IOException;
import java.util.ArrayList;

public class Utilities {

    /* This function makes a system call that will get the running processes (non-kernel), then returns them in a well
     * formatted data structure (ArrayList).
     * @Param None
     * @Return ArrayList<Task> dynamic array with the records of processes (pid, name).
     */
    public ArrayList<Task> populateFile() throws IOException {
        Tasklist tasklist = new Tasklist();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/bin/bash","-c","ps -U root -u root -N -o pid,cmd");
        Process process = processBuilder.start();
        ArrayList<Task> tasks = tasklist.getListFromProcess(process);
        return tasks;
    }

    /* This function makes a system call that will kill a process.
     * @Param int pid, the id of the process
     * @Return none
    */
    public void killTask(int pid) throws IOException {
        String theKill = "kill "+pid;
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/bin/bash","-c",theKill);
        Process process = processBuilder.start();
    }
}
