package persistence;


import model.Task;
import model.ToDoList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(Integer num, String description, Task t) {
        assertEquals(num, t.getTaskNum());
        assertEquals(description, t.getDescription());
    }
}


