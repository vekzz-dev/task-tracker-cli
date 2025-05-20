package org.vekzz_dev.tasktracker.service;

import org.vekzz_dev.tasktracker.model.Status;
import org.vekzz_dev.tasktracker.model.Task;
import org.vekzz_dev.tasktracker.repository.IdRepository;
import org.vekzz_dev.tasktracker.repository.TaskRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final IdRepository idRepository;
    private final TaskRepository taskRepository;
    private final List<Task> taskList;

    public TaskService(IdRepository idRepository, TaskRepository taskRepository) {
        this.idRepository = idRepository;
        this.taskRepository = taskRepository;
        taskList = Optional.ofNullable(taskRepository.findAll())
                .orElseGet(ArrayList::new);
    }

    public void add(String description) {
        int id = idRepository.nextId();
        Status status = Status.IN_PROGRESS;
        LocalDateTime createdAndUpdatedAt = LocalDateTime.now();
        Task task = new Task(id, description, status, createdAndUpdatedAt, createdAndUpdatedAt);
        taskList.add(task);
        taskRepository.save(taskList);
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
