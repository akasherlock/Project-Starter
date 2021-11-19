package persistence;


import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(Integer num, String description, Boolean isTaskCompleted, Task t) {
        assertEquals(num, t.getTaskNum());
        assertEquals(description, t.getDescription());
        assertEquals(isTaskCompleted, t.isCompleted());
    }
}


