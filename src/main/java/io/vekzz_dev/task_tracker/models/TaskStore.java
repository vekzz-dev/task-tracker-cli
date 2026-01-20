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

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}