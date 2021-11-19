package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList toDoList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList toDoList = reader.read();
            assertEquals(0, toDoList.totalTasks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderSomeToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderSomeToDoList.json");
        try {
            ToDoList toDoList = reader.read();
            LinkedList<Task> todo = toDoList.listOfAllTasks();
            assertEquals(2, todo.size());
            checkTask(1, "Math 200 hw", false, todo.get(0));
            checkTask(2, "Project", true, todo.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
