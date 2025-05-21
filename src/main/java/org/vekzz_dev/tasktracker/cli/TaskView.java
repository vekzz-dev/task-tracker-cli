package org.vekzz_dev.tasktracker.cli;

import org.vekzz_dev.tasktracker.model.Task;

import java.util.List;

public class TaskView {
    public void showTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet.");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
