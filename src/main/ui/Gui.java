package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Gui extends JFrame  {

    private ToDoList toDoList = new ToDoList();
    private static final String JSON_STORE = "./data/todolist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // GUI Components:

    JTextArea taskTextArea = new JTextArea();

    JLabel taskNumLabel = new JLabel("Task Number: ");
    JTextField taskNumTextField = new JTextField(10);

    JLabel descriptionLabel = new JLabel("Description: ");
    JTextField descriptionTextField = new JTextField(20);

    JButton addButton = new JButton("Add Task");
    JButton removeButton = new JButton("Remove Task");
    JButton viewAllButton =  new JButton("View all Tasks");
    JButton markButton = new JButton("Mark as Complete");
    JButton saveButton = new JButton("Save");
    JButton loadButton = new JButton("Load");
    JButton progressButton = new JButton("Progress");
    JButton quitButton = new JButton("Quit");

    ImageIcon addIcon = new ImageIcon("data/addButton.png");
    ImageIcon removeIcon = new ImageIcon("data/removeButton.png");
    ImageIcon viewAllIcon = new ImageIcon("data/listIcon.png");
    ImageIcon markIcon = new ImageIcon("data/markedComplete.png");
    ImageIcon saveIcon = new ImageIcon("data/saveButton.png");
    ImageIcon loadIcon = new ImageIcon("data/loadData.png");
    ImageIcon progressIcon = new ImageIcon("data/progressbutton.png");
    ImageIcon quitIcon = new ImageIcon("data/quit.png");

    // Class instance data:
    public Gui() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        JPanel flow1Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow1Panel.setBackground(new Color(255,209,220));

        JPanel flow2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow2Panel.setBackground(new Color(252,246,245));

        JPanel flow3Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        flow3Panel.setBackground(new Color(252,246,245));

        JPanel gridPanel = new JPanel(new GridLayout(3,1));

        taskTextArea.setEditable(false);
        taskTextArea.setBackground(new Color(173,216,230));

        buttonsIconsResize();

        settingPanels(flow1Panel, flow2Panel, flow3Panel, gridPanel);

        add(taskTextArea, BorderLayout.CENTER);
        add(gridPanel, BorderLayout.SOUTH);

        addButton.addActionListener(event -> addTask());
        viewAllButton.addActionListener(event -> viewAllTasks());
        quitButton.addActionListener(event -> quitApplication());
        removeButton.addActionListener(event -> removeTask());
        markButton.addActionListener(event -> markTaskCompleted());
        progressButton.addActionListener(event -> progressOfTasks());
        saveButton.addActionListener(event -> saveData());
        loadButton.addActionListener(event -> loadData());
    }

    // MODIFIES: this
    // EFFECTS: adds textFields/labels to panels
    private void settingPanels(JPanel flow1Panel, JPanel flow2Panel, JPanel flow3Panel, JPanel gridPanel) {
        flow1Panel.add(taskNumLabel);
        flow1Panel.add(taskNumTextField);
        flow1Panel.add(descriptionLabel);
        flow1Panel.add(descriptionTextField);

        addingButtons(flow2Panel, flow3Panel);

        addingPanels(flow1Panel, flow2Panel, flow3Panel, gridPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to panels
    private void addingButtons(JPanel flow2Panel, JPanel flow3Panel) {
        flow2Panel.add(addButton);
        flow2Panel.add(removeButton);
        flow2Panel.add(viewAllButton);
        flow2Panel.add(markButton);
        flow3Panel.add(progressButton);
        flow3Panel.add(saveButton);
        flow3Panel.add(loadButton);
        flow3Panel.add(quitButton);
    }

    // MODIFIES: this
    // EFFECTS: adds panels to gridPanel
    private void addingPanels(JPanel flow1Panel, JPanel flow2Panel, JPanel flow3Panel, JPanel gridPanel) {
        gridPanel.add(flow1Panel);
        gridPanel.add(flow2Panel);
        gridPanel.add(flow3Panel);
    }

    // EFFECTS: resizes Icon image of all buttons to desired size.
    private void buttonsIconsResize() {
        Image addResize = addIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon addIconResize = new ImageIcon(addResize);

        Image removeResize = removeIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon removeIconResize = new ImageIcon(removeResize);

        Image viewAllResize = viewAllIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon viewAllIconResize = new ImageIcon(viewAllResize);

        Image markAsResize = markIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon markAsIconResize = new ImageIcon(markAsResize);

        Image saveResize = saveIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon saveIconResize = new ImageIcon(saveResize);

        Image loadResize = loadIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon loadIconResize = new ImageIcon(loadResize);

        Image progressResize = progressIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon progressIconResize = new ImageIcon(progressResize);

        Image quitResize = quitIcon.getImage().getScaledInstance(20,20,20);
        ImageIcon quitIconResize = new ImageIcon(quitResize);

        setIconFlow2PanelButtons(addIconResize, removeIconResize, viewAllIconResize, markAsIconResize);

        setIconFlow3PanelButtons(saveIconResize, loadIconResize, progressIconResize, quitIconResize);
    }

    // MODIFIES: this
    // EFFECTS: sets Icon Image of buttons in flow3Panel
    private void setIconFlow3PanelButtons(ImageIcon saveIconResize, ImageIcon loadIconResize,
                                          ImageIcon progressIconResize, ImageIcon quitIconResize) {
        saveButton.setIcon(saveIconResize);
        loadButton.setIcon(loadIconResize);
        progressButton.setIcon(progressIconResize);
        quitButton.setIcon(quitIconResize);
    }

    // MODIFIES: this
    // EFFECTS: sets Icon Image of buttons in flow2Panel
    private void setIconFlow2PanelButtons(ImageIcon addIconResize, ImageIcon removeIconResize,
                                          ImageIcon viewAllIconResize, ImageIcon markAsIconResize) {
        addButton.setIcon(addIconResize);
        removeButton.setIcon(removeIconResize);
        viewAllButton.setIcon(viewAllIconResize);
        markButton.setIcon(markAsIconResize);
    }

    // EFFECTS: loads todolist from file
    private void loadData() {
        try {
            toDoList = jsonReader.read();
            viewAllTasks();
            JOptionPane.showMessageDialog(null, "Database was Loaded.");
        } catch (IOException e) {
            System.out.println("Unable to read from file:");
        }
    }

    // EFFECTS: saves the current displayed todolist to file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Database was Saved.");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // EFFECTS: displays the number of incomplete and completed tasks in todolist
    private void progressOfTasks() {
        taskTextArea.setText("");
        taskTextArea.append("Number of Completed Tasks: " + toDoList.numberOfCompleteTasks() + "\n"
                + "Number of Incomplete Tasks: " + toDoList.numberOfIncompleteTasks());
    }

    // REQUIRES: taskNumTextField.getText() is an integer (string)
    // MODIFIES: this
    // EFFECTS: Marks task with the given task number as complete
    private void markTaskCompleted() {
        if (!isTaskInToDoList(taskNumTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Error: Task to be marked is not in the database.");
        } else {
            toDoList.markAsComplete(Integer.parseInt(taskNumTextField.getText()));
            viewAllTasks();
            JOptionPane.showMessageDialog(null, "Task was marked as complete.");

            taskNumTextField.setText("");
            descriptionTextField.setText("");
        }
    }

    // EFFECTS: Checks if the task is in the todolist
    private boolean isTaskInToDoList(String numStr) {
        boolean isTaskUnique = true;

        for (Task task : toDoList.listOfAllTasks()) {
            if (String.valueOf(task.getTaskNum()).compareToIgnoreCase(numStr) == 0) {
                isTaskUnique = false;
                break;
            }
        }
        return !isTaskUnique;
    }

    // MODIFIES: this
    // EFFECTS: removes task with the given task number from the list
    private void removeTask() {
        if (toDoList.totalTasks() == 0) {
            JOptionPane.showMessageDialog(null, "Error: Database is Empty!");
        } else if (!isTaskInToDoList(taskNumTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Error: Task to be removed is not in the database.");
        } else {
            String taskNumStr = taskNumTextField.getText();
            int taskNum = Integer.parseInt(taskNumStr);
            toDoList.removeTask(taskNum);

            viewAllTasks();

            taskNumTextField.setText("");
            descriptionTextField.setText("");
        }
    }

    // EFFECTS: quits the application
    private void quitApplication() {
        JOptionPane.showMessageDialog(null, "Bye! GoodLuck with your work.");
        System.exit(0);
    }

    // EFFECTS: displays all tasks in taskTextArea
    private void viewAllTasks() {
        taskTextArea.setText("");
        for (Task task : toDoList.listOfAllTasks()) {
            taskTextArea.append(task + "\n");
        }
    }

    // REQUIRES: taskNumTextField.getText() is an integer (string)
    // MODIFIES: this
    // EFFECTS: adds task to todolist and displays in taskTextArea
    private void addTask() {
        if (isTaskInToDoList(taskNumTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Error: Task Number is already assigned to a different task.");
        } else {
            String taskNumStr = taskNumTextField.getText();
            int taskNum = Integer.parseInt(taskNumStr);
            String description = descriptionTextField.getText();
            toDoList.addTask(new Task(taskNum,description));

            viewAllTasks();

            taskNumTextField.setText("");
            descriptionTextField.setText("");
        }
    }

}
