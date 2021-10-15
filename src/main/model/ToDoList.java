package model;

import java.util.LinkedList;

public class ToDoList {

    private LinkedList<Task> toDoList;

    public ToDoList() {
        toDoList = new LinkedList<>();
    }

    // REQUIRES: a task number not already in to-do list
    // MODIFIES: this
    // EFFECTS: adds a task to to-do list
    public void addTask(Task t) {
        toDoList.add(t);
    }

    // MODIFIES: this
    // EFFECTS: marks a task with given task number as complete
    public void markAsComplete(Integer i) {
        for (Task next : toDoList) {
            if (next.getTaskNum() == i) {
                next.taskCompleted();
            }
        }
    }

    // REQUIRES: task with the task number should be in list
    // MODIFIES: this
    // EFFECTS:  removes a task from to-do list
    public void removeTask(Integer i) {
        toDoList.removeIf(next -> next.getTaskNum() == i);
    }

    // EFFECTS: shows the number of incomplete and completed tasks
    public Integer numberOfCompleteTasks() {
        int completeTasks = 0;

        for (Task next : toDoList) {
            if (next.isCompleted()) {
                completeTasks = completeTasks + 1;
            }
        }
        return completeTasks;
    }

    // EFFECTS: shows the number of incomplete and completed tasks
    public Integer numberOfIncompleteTasks() {
        int incompleteTasks = 0;

        for (Task next : toDoList) {
            if (!next.isCompleted()) {
                incompleteTasks = incompleteTasks + 1;
            }
        }
        return incompleteTasks;
    }

    // EFFECTS: returns the number of tasks in the to-do list
    public Integer totalTasks() {
        return toDoList.size();
    }

    public LinkedList<Task> listOfAllTasks() {
        return this.toDoList;
    }

    /*// EFFECTS: displays all the tasks in the to-do list
    public void viewAllTasks() {
        if (toDoList.size() == 0) {
            System.out.println("No Tasks yet!");
        } else {
            for (Task next : toDoList) {
                System.out.println("\nTask Number: " + String.valueOf(next.getTaskNum()) + " , Description: "
                        + next.getDescription() + " , Is Task Completed?: " + String.valueOf(next.isCompleted()));
            }
        }
    }*/
}




