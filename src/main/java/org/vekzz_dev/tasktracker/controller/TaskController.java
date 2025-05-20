package org.vekzz_dev.tasktracker.controller;

import org.vekzz_dev.tasktracker.service.TaskService;

import java.util.Map;

public class TaskController {
    private final TaskService service;

    private static final Map<String, int[]> expectedArgsByCommand = Map.of(
            "add", new int[]{1, 1},
            "update", new int[]{2, 2},
            "delete", new int[]{1, 1},
            "mark", new int[]{2, 2},
            "list", new int[]{0, 1}
    );

    public TaskController(TaskService service) {
        this.service = service;
    }

    public void addTask(String[] args) {
    }

    public void updateTask(String[] args) {
    }

    public void listTask(String[] args) {
    }

    public void markTask(String[] args) {
    }

    public void deleteTask(String[] args) {
    }

    private void isNumberOfArgsCorrect(String[] args, String action) {
        int[] range = expectedArgsByCommand.get(action);
        int argCount = args.length;
        int min = range[0];
        int max = range[1];
        if (argCount < min || argCount > max) {
            String rangeMsg = (min == max)
                    ? min + " argumento(s)"
                    : "entre " + min + " y " + max + " argumento(s)";
            throw new IllegalArgumentException("El comando '" + action + "' requiere " +
                    rangeMsg + " , pero se recibieron " + args.length + ".");
        }
    }
}
