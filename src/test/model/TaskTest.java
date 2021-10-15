package model;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
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

    @Test
    public void getTaskNumTest() {
        assertEquals(1, t1.getTaskNum());
        assertEquals(10, t10.getTaskNum());
        assertEquals(5, t5.getTaskNum());
        assertEquals(4, t4.getTaskNum());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("MATH200 HW1", t1.getDescription());
        assertEquals("SCI113 Presentation", t6.getDescription());
        assertEquals("MATH221 Webwork 5", t5.getDescription());
        assertEquals("MATH221 Assignment 3", t3.getDescription());
    }

    @Test
    public void isCompletedTest() {
        assertFalse(t1.isCompleted());
        t1.taskCompleted();
        assertTrue(t1.isCompleted());

        assertFalse(t7.isCompleted());
    }

    @Test
    public void taskCompletedTest() {
        assertFalse(t2.isCompleted());
        t2.taskCompleted();
        assertTrue(t2.isCompleted());

        assertFalse(t7.isCompleted());
        t7.taskCompleted();
        assertTrue(t7.isCompleted());
    }

}
