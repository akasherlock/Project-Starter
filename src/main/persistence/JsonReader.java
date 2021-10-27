package persistence;

import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads todolist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList toDoList = new ToDoList();
        addToDoList(toDoList, jsonObject);
        return toDoList;
    }

    // MODIFIES: toDoList
    // EFFECTS: parses toDoList from JSON object and adds them to toDoList
    private void addToDoList(ToDoList toDoList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("todolist");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(toDoList, nextTask);
        }
    }

    // MODIFIES: toDoList
    // EFFECTS: parses task from JSON object and adds it to toDoList
    private void addTask(ToDoList toDoList, JSONObject jsonObject) {
        int taskNum = jsonObject.getInt("taskNum");
        String description = String.valueOf(jsonObject.getString("description"));
        Task task = new Task(taskNum, description);
        toDoList.addTask(task);
    }


}
