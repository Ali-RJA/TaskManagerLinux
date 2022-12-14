public class Task {
    String taskName;
    int pid;
    boolean checked;

    public Task(String taskName, int pid, boolean checked) {
        this.taskName = taskName;
        this.pid = pid;
        this.checked = checked;
    }

    public Task(int pid,String taskName) {
        this.taskName = taskName;
        this.pid = pid;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
