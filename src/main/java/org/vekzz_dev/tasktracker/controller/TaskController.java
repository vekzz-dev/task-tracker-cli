package org.vekzz_dev.tasktracker.controller;

import org.vekzz_dev.tasktracker.cli.TaskView;
import org.vekzz_dev.tasktracker.service.TaskService;

import java.util.Map;

public class TaskController {
    private final TaskService service;
    private final TaskView view;

    private static final Map<String, int[]> expectedArgsByCommand = Map.of(
            "add", new int[]{1, 1},
            "update", new int[]{2, 2},
            "delete", new int[]{1, 1},
            "mark", new int[]{2, 2},
            "list", new int[]{0, 1}
    );
    private static final Map<String, String> statusStringMap = Map.of(
            "done", "DONE",
            "todo", "TODO",
            "in-progress", "IN_PROGRESS"
    );

    public TaskController(TaskService service, TaskView view) {
        this.service = service;
        this.view = view;
    }

    public void addTask(String[] args) {
        isNumberOfArgsCorrect(args, "add");
        String description = args[0];
        int id = service.add(description);
        view.showMessage("Tarea agregada correctamente (ID: " + id + ")");
    }

    public void updateTask(String[] args) {
        isNumberOfArgsCorrect(args, "update");
        int id = parseId(args[0]);
        String description = args[1];
        service.update(id, description);
        view.showMessage("Tarea actualizada correctamente (ID: " + id + ")");
    }

    public void listTask(String[] args) {
        isNumberOfArgsCorrect(args, "list");
        if (args.length == 0) {
            view.showMessage("Mostrando todas las tareas: ");
            view.showTasks(service.list(""));
        } else {
            String statusKey = args[0].toLowerCase();
            String status = validateStatus(statusKey);
            view.showMessage("Mostrando las tareas con estado: " + statusKey);
            view.showTasks(service.list(status));
        }
    }

    public void markTask(String[] args) {
        isNumberOfArgsCorrect(args, "mark");
        int id = parseId(args[0]);
        String status = validateStatus(args[1]);
        service.mark(id, status);
        view.showMessage("Tarea marcada correctamente (ID: " + id + ")");
    }

    public void deleteTask(String[] args) {
        isNumberOfArgsCorrect(args, "delete");
        int id = parseId(args[0]);
        service.delete(id);
        view.showMessage("Tarea eliminada correctamente (ID: " + id + ")");
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

    private int parseId(String idArg) {
        try {
            return Integer.parseInt(idArg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: el ID debe ser un número entero válido, " +
                    "recibido: " + idArg);
        }
    }

    private String validateStatus(String statusArg) {
        String status = statusStringMap.get(statusArg.toLowerCase());
        if (status == null) throw new IllegalArgumentException("Error: el estado '" + statusArg
                + "' no es válido. Estados válidos: "
                + statusStringMap.keySet());
        return status;
    }
}
