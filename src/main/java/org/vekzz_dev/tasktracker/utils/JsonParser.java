package org.vekzz_dev.tasktracker.utils;

import org.vekzz_dev.tasktracker.model.Status;
import org.vekzz_dev.tasktracker.model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonParser {
    private static String serialize(Task t) {
        StringBuilder sbObject = new StringBuilder();
        sbObject.append("{\"").append("id").append("\":\"").append(t.getId()).append("\"");
        sbObject.append(",\"").append("description").append("\":\"").append(t.getDescription()).append("\"");
        sbObject.append(",\"").append("status").append("\":\"").append(t.getStatus()).append("\"");
        sbObject.append(",\"").append("createdAt").append("\":\"").append(t.getCreatedAt()).append("\"");
        sbObject.append(",\"").append("updatedAt").append("\":\"").append(t.getUpdatedAt()).append("\"").append("}");
        return sbObject.toString();
    }

    private static List<List<String>> deserialize(String jsonString) {
        if (jsonString == null || jsonString.isBlank()) return new ArrayList<>();
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        String[] jsonObject = jsonString.split("},");
        return Arrays.stream(jsonObject)
                .map(s -> s.replace("{", ""))
                .map(e -> e.replace("}", ""))
                .map(o -> Arrays.stream(o.split(","))
                        .map(p -> p.substring(p.lastIndexOf("\":") + 2))
                        .map(e -> e.replace("\"", "")).toList()
                ).toList();
    }

    public static String convertToJson(List<Task> taskList) {
        StringBuilder sbList = new StringBuilder();
        sbList.append("[");
        sbList.append(taskList.stream()
                .map(JsonParser::serialize)
                .collect(Collectors.joining(",")));
        sbList.append("]");
        return sbList.toString();
    }

    public static List<Task> convertToTaskList(String jsonString) {
        return deserialize(jsonString).stream()
                .map(v -> {
                    int id = Integer.parseInt(v.getFirst());
                    String desc = v.get(1);
                    Status status = Status.valueOf(v.get(2));
                    LocalDateTime createdAt = LocalDateTime.parse(v.get(3));
                    LocalDateTime updatedAt = LocalDateTime.parse(v.get(4));
                    return new Task(id, desc, status, createdAt, updatedAt);
                }).toList();
    }
}
