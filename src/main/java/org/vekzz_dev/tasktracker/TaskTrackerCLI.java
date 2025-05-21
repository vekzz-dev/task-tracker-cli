package org.vekzz_dev.tasktracker;

import org.vekzz_dev.tasktracker.cli.CommandManager;
import org.vekzz_dev.tasktracker.cli.TaskView;
import org.vekzz_dev.tasktracker.controller.TaskController;
import org.vekzz_dev.tasktracker.repository.FileIdRepository;
import org.vekzz_dev.tasktracker.repository.FileTaskRepository;
import org.vekzz_dev.tasktracker.repository.IdRepository;
import org.vekzz_dev.tasktracker.repository.TaskRepository;
import org.vekzz_dev.tasktracker.service.TaskService;

public class TaskTrackerCLI {
    public static void main(String[] args) {
        TaskView taskView = new TaskView();
        IdRepository idRepository = new FileIdRepository();
        TaskRepository taskRepository = new FileTaskRepository();
        TaskService taskService = new TaskService(idRepository, taskRepository);
        TaskController taskController = new TaskController(taskService, taskView);
        CommandManager commandManager = new CommandManager(taskController);
        try {
            commandManager.run();
        } catch (Exception e) {
            taskView.showMessage("Fatal error: " + e.getMessage());
        }
    }
}
