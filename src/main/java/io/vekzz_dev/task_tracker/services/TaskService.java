package io.vekzz_dev.task_tracker.services;

import io.vekzz_dev.task_tracker.models.Status;
import io.vekzz_dev.task_tracker.models.Task;
import io.vekzz_dev.task_tracker.models.TaskStore;
import io.vekzz_dev.task_tracker.repositories.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TaskService {

    private final Repository<TaskStore> taskRepository;

    public TaskService(Repository<TaskStore> taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> list(String status) {
        List<Task> tasks = getStore().getTasks();

        if (status.equals("all")) return tasks;

        Status selectedStatus = Status.fromArgument(status);

         return tasks.stream()
                .filter(task -> task.getStatus().equals(selectedStatus))
                .toList();
    }

    public int add(String description) {
        List<Task> tasks = getStore().getTasks();

        Task task = new Task(
                getStore().generateNextId(),
                description,
                Status.TODO,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        tasks.add(task);
        taskRepository.save(getStore());
        return task.getId();
    }

    public void update(int id, String description) {
        List<Task> tasks = getStore().getTasks();
        Task taskToUpdate = getTaskById(id, tasks);

        taskToUpdate.setDescription(description);
        taskToUpdate.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(getStore());
    }

    public void delete(int id) {
        List<Task> tasks = getStore().getTasks();
        Task taskToDelete = getTaskById(id, tasks);

        tasks.remove(taskToDelete);
        taskRepository.save(getStore());
    }

    public void mark(int id, String status) {
        List<Task> tasks = getStore().getTasks();
        Task taskToMark = getTaskById(id, tasks);
        Status newStatus = Status.fromArgument(status);


        taskToMark.setStatus(newStatus);
        taskToMark.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(getStore());
    }

    private TaskStore getStore() {
        return taskRepository.getAll();
    }

    private Task getTaskById(int id, List<Task> tasks) {
        Optional<Task> taskOptional = tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();

        return taskOptional.orElseThrow(() -> new NoSuchElementException("Task not found with id: " + id));
    }
}