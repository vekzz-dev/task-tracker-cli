package io.vekzz_dev.task_tracker.utils;

import io.vekzz_dev.task_tracker.services.TaskService;

public class TaskServiceHolder {

    private static TaskService taskService;

    public static TaskService getTaskService() {
        return taskService;
    }

    public static void setTaskService(TaskService taskService) {
        TaskServiceHolder.taskService = taskService;
    }
}