package org.vekzz_dev.tasktracker.cli;

import org.vekzz_dev.tasktracker.controller.TaskController;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CommandManager {
    private static final Map<String, Consumer<String[]>> commands = new HashMap<>();

    public static void main(String[] args) {
        loadCommands();
    }

    private static void loadCommands() {
        commands.put("add", TaskController::addTask);
        commands.put("list", TaskController::listTask);
        commands.put("update", TaskController::updateTask);
        commands.put("mark", TaskController::markTask);
        commands.put("delete", TaskController::deleteTask);
    }
}
