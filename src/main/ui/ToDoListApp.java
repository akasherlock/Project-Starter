package ui;

import model.Task;
import model.ToDoList;

import java.util.Scanner;

public class ToDoListApp {
    private ToDoList toDoList;
    private Scanner input;

    public ToDoListApp() {
        runToDoList();
    }

    // EFFECTS: runs the todolist application
    private void runToDoList() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGood luck with your work!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addTaskToToDoList();
        } else if (command.equals("r")) {
            removeTaskFromToDoList();
        } else if (command.equals("m")) {
            markTaskAsCompleteFromToDoList();
        } else if (command.equals("n")) {
            numberOfCompleteAndIncompleteTasks();
        } else if (command.equals("v")) {
            viewAllTasks();
        } else {
            System.out.println("Selection not valid...");
        }

    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add task to to-do list");
        System.out.println("\tr -> remove task from to-do list");
        System.out.println("\tm -> mark a task as complete");
        System.out.println("\tn -> show the number of incomplete and completed tasks");
        System.out.println("\tv -> view all tasks in the todo-list");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes to-do list
    private void init() {
        toDoList = new ToDoList();
        input = new Scanner(System.in);
    }

    // REQUIRES: a task number not already in to-do list
    // MODIFIES: this
    // EFFECTS: adds a task to to-do list
    private void addTaskToToDoList() {
        System.out.println("\nEnter Task number: ");
        String taskNumInStr = input.next();
        int taskNum = Integer.parseInt(taskNumInStr);

        System.out.println("\nEnter Task description: ");
        String description = input.next();

        Task t = new Task(taskNum, description);

        toDoList.addTask(t);
        System.out.println("\nThe task was successfully added!");
    }

    // REQUIRES: task should be in to-do list
    // MODIFIES: this
    // EFFECTS: removes a task from to-do list
    private void removeTaskFromToDoList() {
        System.out.println("\nEnter Task number: ");
        String taskNumInStr = input.next();
        int taskNum = Integer.parseInt(taskNumInStr);

        toDoList.removeTask(taskNum);
        System.out.println("\nThe task was successfully removed!");
    }

    // REQUIRES: task should be in to-do list
    // MODIFIES: this
    // EFFECTS: marks the given task as complete
    private void markTaskAsCompleteFromToDoList() {
        System.out.println("\nEnter Task number: ");
        String taskNumInStr = input.next();
        int taskNum = Integer.parseInt(taskNumInStr);

        toDoList.markAsComplete(taskNum);
        System.out.println("\nThe task was marked as complete!");
    }

    // EFFECTS: shows the number of incomplete and completed tasks
    private void numberOfCompleteAndIncompleteTasks() {
        int completeTaskNum = toDoList.numberOfCompleteTasks();
        int incompleteTaskNum = toDoList.numberOfIncompleteTasks();

        String completeTaskStr = String.valueOf(completeTaskNum);
        String incompleteTaskStr = String.valueOf(incompleteTaskNum);

        System.out.println("\nNumber of Tasks completed: " + completeTaskStr + " Number of Tasks not completed: "
                + incompleteTaskStr);
    }

    // EFFECTS: displays all the tasks in the to-do list
    public void viewAllTasks() {
        if (toDoList.totalTasks() == 0) {
            System.out.println("No Tasks!");
        } else {
            for (Task next : toDoList.listOfAllTasks()) {
                System.out.println("\nTask Number: " + next.getTaskNum() + " , Description: "
                        + next.getDescription() + " , Is Task Completed?: " + next.isCompleted());
            }
        }
    }
}














