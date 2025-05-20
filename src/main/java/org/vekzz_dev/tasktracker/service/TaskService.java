package org.vekzz_dev.tasktracker.service;

import org.vekzz_dev.tasktracker.repository.IdRepository;
import org.vekzz_dev.tasktracker.repository.TaskRepository;

public class TaskService {
    private final IdRepository idRepository;
    private final TaskRepository taskRepository;

    public TaskService(IdRepository idRepository, TaskRepository taskRepository) {
        this.idRepository = idRepository;
        this.taskRepository = taskRepository;
    }

    public void add(String description) {
    }

    public void update(int id, String description) {
    }

    public void delete(int id) {
    }

    public void mark(int id, String status) {
    }

    public void list(String status) {
    }
}
