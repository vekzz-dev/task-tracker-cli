package io.vekzz_dev.task_tracker.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vekzz_dev.task_tracker.models.TaskStore;
import io.vekzz_dev.task_tracker.storage.FileManager;

public class TaskRepository implements Repository<TaskStore> {

    private final ObjectMapper mapper;
    private final FileManager fileManager;

    public TaskRepository(ObjectMapper mapper, FileManager fileManager) {
        this.mapper = mapper;
        this.fileManager = fileManager;
    }

    @Override
    public TaskStore getAll() {
        String json = fileManager.read();

        if (json == null || json.isBlank()) return new TaskStore();

        try {
            return mapper.readValue(json, TaskStore.class);

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to parse json", e);
        }
    }

    @Override
    public void save(TaskStore taskStore) {
        try {
            String json = mapper.writeValueAsString(taskStore);
            fileManager.write(json);

        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to write json", e);
        }
    }
}