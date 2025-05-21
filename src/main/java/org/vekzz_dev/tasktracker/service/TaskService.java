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

    public int add(String description) {
        int id = idRepository.nextId();
        Status status = Status.IN_PROGRESS;
        LocalDateTime createdAndUpdatedAt = LocalDateTime.now();
        Task task = new Task(id, description, status, createdAndUpdatedAt, createdAndUpdatedAt);
        taskList.add(task);
        taskRepository.save(taskList);
        return id;
    }

    public void update(int id, String description) {
        Task task = findById(id);
        task.setDescription(description);
        task.setUpdatedAt(LocalDateTime.now());
        taskRepository.save(taskList);
    }

    public void delete(int id) {
        Task task = findById(id);
        taskList.remove(task);
        taskRepository.save(taskList);
    }

    private Task findById(int id) {
        return taskList.stream()
                .filter(t -> t.getId() == id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("No se encontró la tarea con id: " + id));
    }

    public void mark(int id, String status) {
        Task task = findById(id);
        task.setStatus(Status.valueOf(status));
        taskRepository.save(taskList);
    }

    public List<Task> list(String status) {
        List<Task> allTask = taskRepository.findAll();
        if (status == null || status.isEmpty()) return allTask;
        return allTask.stream()
                .filter(t -> t.getStatus() == Status.valueOf(status))
                .toList();
    }
}
