package io.vekzz_dev.task_tracker.models;

import java.util.ArrayList;
import java.util.List;

public class TaskStore {

    private int lastId;
    private List<Task> tasks;

    public TaskStore() {
        this.tasks = new ArrayList<>();
        this.lastId = 0;
    }

    public int generateNextId() {
        return ++this.lastId;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}