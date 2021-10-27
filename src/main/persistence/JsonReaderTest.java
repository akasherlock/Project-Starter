package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import persistence.JsonTest;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
            assertEquals(0, toDoList.numberOfIncompleteTasks());
            assertEquals(0, toDoList.numberOfCompleteTasks());

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
            checkTask(1, "Math 200 hw", todo.get(0));
            checkTask(2, "Project", todo.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
