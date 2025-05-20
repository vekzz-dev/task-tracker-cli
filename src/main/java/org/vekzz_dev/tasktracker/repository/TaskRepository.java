package org.vekzz_dev.tasktracker.repository;

import org.vekzz_dev.tasktracker.model.Task;

import java.util.List;

public interface TaskRepository {
    void save(Task task);

    List<Task> findAll();
}
