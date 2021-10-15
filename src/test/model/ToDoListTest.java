package model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    ToDoList toDoList;

    Task t1= new Task(1, "MATH200 HW1");
    Task t2= new Task(2, "CPSC210 reading 4");
    Task t3= new Task(3, "MATH221 Assignment 3");
    Task t4= new Task(4, "STAT200 Lab 6");
    Task t5= new Task(5, "MATH221 Webwork 5");
    Task t6= new Task(6, "SCI113 Presentation");
    Task t7= new Task(7, "BIO111 Project");
    Task t8= new Task(8, "CPSC110 P-SET 9");
    Task t9= new Task(9, "CPSC121 Worksheet 8");
    Task t10= new Task(10, "MATH200 Practice Quiz");

    @BeforeEach
    public void CreateNewToDoList() {
        toDoList = new ToDoList();
    }

    @Test
    public void addSomeTasks() {
        toDoList.addTask(t1);
        assertEquals(toDoList.totalTasks() , 1);
        assertEquals(toDoList.listOfAllTasks().get(0), t1);

        toDoList.addTask(t4);
        assertEquals(toDoList.totalTasks() , 2);
        assertEquals(toDoList.listOfAllTasks().get(1), t4);
    }

    @Test
    public void addManyTasks() {
        toDoList.addTask(t1);
        assertEquals(toDoList.totalTasks() , 1);
        assertEquals(toDoList.listOfAllTasks().get(0), t1);

        toDoList.addTask(t2);
        assertEquals(toDoList.totalTasks() , 2);
        assertEquals(toDoList.listOfAllTasks().get(1), t2);

        toDoList.addTask(t3);
        assertEquals(toDoList.totalTasks() , 3);

        toDoList.addTask(t4);
        assertEquals(toDoList.totalTasks() , 4);

        toDoList.addTask(t5);
        assertEquals(toDoList.totalTasks() , 5);

        toDoList.addTask(t6);
        assertEquals(toDoList.totalTasks() , 6);

        toDoList.addTask(t7);
        assertEquals(toDoList.totalTasks() , 7);

        toDoList.addTask(t8);
        assertEquals(toDoList.totalTasks() , 8);

        toDoList.addTask(t9);
        assertEquals(toDoList.totalTasks() , 9);

        toDoList.addTask(t10);
        assertEquals(toDoList.totalTasks() , 10);
        assertEquals(toDoList.listOfAllTasks().get(9), t10);

    }

    @Test
    public void markAsCompleteTest() {

        assertFalse(t1.isCompleted());

        toDoList.addTask(t1);
        toDoList.addTask(t2);
        toDoList.addTask(t3);
        toDoList.addTask(t4);
        toDoList.addTask(t5);

        assertEquals(toDoList.numberOfCompleteTasks(),0);
        assertEquals(toDoList.numberOfIncompleteTasks(),5);

        toDoList.markAsComplete(1);
        assertEquals(toDoList.listOfAllTasks().get(0), t1);
        assertTrue(toDoList.listOfAllTasks().get(0).isCompleted());

        toDoList.markAsComplete(2);
        assertEquals(toDoList.numberOfCompleteTasks(),2);
        assertEquals(toDoList.numberOfIncompleteTasks(),3);

        toDoList.markAsComplete(2);
        assertEquals(toDoList.numberOfCompleteTasks(),2);
        assertEquals(toDoList.numberOfIncompleteTasks(),3);

        toDoList.markAsComplete(3);
        assertEquals(toDoList.numberOfCompleteTasks(),3);
        assertEquals(toDoList.numberOfIncompleteTasks(),2);

        toDoList.markAsComplete(69);
        assertEquals(toDoList.numberOfCompleteTasks(),3);
        assertEquals(toDoList.numberOfIncompleteTasks(),2);

        toDoList.markAsComplete(4);
        toDoList.markAsComplete(5);

        assertEquals(toDoList.numberOfCompleteTasks(),5);
        assertEquals(toDoList.numberOfIncompleteTasks(),0);

    }
    @Test
    public void removeTaskTest() {
        toDoList.addTask(t1);
        toDoList.addTask(t2);
        toDoList.addTask(t3);
        toDoList.addTask(t4);
        toDoList.addTask(t5);

        assertEquals(toDoList.listOfAllTasks().get(0), t1);
        toDoList.removeTask(1);
        assertEquals(toDoList.totalTasks(),4);
        assertNotEquals(toDoList.listOfAllTasks().get(0).getTaskNum(), t1.getTaskNum());

        toDoList.removeTask(2);
        assertEquals(toDoList.totalTasks(),3);

        toDoList.removeTask(3);
        toDoList.removeTask(4);
        toDoList.removeTask(5);
        assertEquals(toDoList.totalTasks(),0);
    }

    @Test
    public void numberOfCompleteTasksTest() {
        toDoList.addTask(t1);
        toDoList.addTask(t2);
        toDoList.addTask(t3);
        toDoList.addTask(t4);
        toDoList.addTask(t5);

        assertEquals(toDoList.numberOfCompleteTasks(),0);

        toDoList.markAsComplete(2);
        assertEquals(toDoList.numberOfCompleteTasks(),1);

        toDoList.markAsComplete(3);
        assertEquals(toDoList.numberOfCompleteTasks(),2);

        toDoList.markAsComplete(4);
        toDoList.markAsComplete(5);
        assertEquals(toDoList.numberOfCompleteTasks(),4);

        toDoList.markAsComplete(1);
        assertEquals(toDoList.numberOfCompleteTasks(),5);



    }

    @Test
    public void numberOfIncompleteTasksTest() {
        toDoList.addTask(t1);
        toDoList.addTask(t2);
        toDoList.addTask(t3);
        toDoList.addTask(t4);
        toDoList.addTask(t5);

        assertEquals(toDoList.numberOfIncompleteTasks(),5);

        toDoList.markAsComplete(2);
        assertEquals(toDoList.numberOfIncompleteTasks(),4);

        toDoList.markAsComplete(3);
        assertEquals(toDoList.numberOfIncompleteTasks(),3);

        toDoList.markAsComplete(4);
        toDoList.markAsComplete(5);
        assertEquals(toDoList.numberOfIncompleteTasks(),1);

        toDoList.markAsComplete(1);
        assertEquals(toDoList.numberOfIncompleteTasks(),0);
    }

    @Test
    public void totalTasksTest() {
        assertEquals(toDoList.totalTasks(), 0);

        toDoList.addTask(t1);
        toDoList.addTask(t2);
        toDoList.addTask(t3);
        assertEquals(toDoList.totalTasks(), 3);

        toDoList.addTask(t4);
        toDoList.addTask(t5);
        assertEquals(toDoList.totalTasks(), 5);
    }

    @Test
    public void listOfAllTasksTest() {
        toDoList.addTask(t1);
        toDoList.addTask(t2);
        toDoList.addTask(t3);

        assertEquals(toDoList.listOfAllTasks().get(0), t1);
        assertEquals(toDoList.listOfAllTasks().get(1), t2);
        assertEquals(toDoList.listOfAllTasks().get(2), t3);
    }







}


