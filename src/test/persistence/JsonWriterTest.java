package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ToDoList toDoList = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            toDoList = reader.read();
            assertEquals(0, toDoList.totalTasks());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Task t1 = new Task(1, "MATH200 HW1");
            Task t2 = new Task(2, "Project");
            ToDoList toDoList = new ToDoList();
            toDoList.addTask(t1);
            toDoList.addTask(t2);
            JsonWriter writer = new JsonWriter("./data/testWriterSomeToDoList.json");
            writer.open();
            writer.write(toDoList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSomeToDoList.json");
            toDoList = reader.read();
            LinkedList<Task> toDo = toDoList.listOfAllTasks();
            assertEquals(2, toDo.size());
            checkTask(1, "MATH200 HW1", toDo.get(0));
            checkTask(2, "Project", toDo.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
