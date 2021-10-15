package model;

// Represents a task having a (unique) task number, description and complete/incomplete status
public class Task {
    private int taskNum;
    private String description;
    private boolean isCompleted;

    public Task(int taskNum, String description) {
        this.taskNum = taskNum;
        this.description = description;
        isCompleted = false;
    }

    // EFFECTS: returns task number
    public int getTaskNum() {
        return taskNum;
    }

    // EFFECTS: returns description
    public String getDescription() {
        return description;
    }

    // EFFECTS: returns true if the task is completed, false otherwise
    public boolean isCompleted() {
        return isCompleted;
    }

    // MODIFIES: this
    // EFFECTS: task is completed
    public void taskCompleted() {
        isCompleted = true;
    }

}