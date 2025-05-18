package org.vekzz_dev.tasktracker.cli;

import org.vekzz_dev.tasktracker.controller.TaskController;
import org.vekzz_dev.tasktracker.exception.CommandNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class CommandManager {
    private static final Map<String, Consumer<String[]>> commands = new HashMap<>();

    public static void main(String[] args) {
        loadCommands();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Task-Tracker >");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("No se proporcionó ningún comando");
                continue;
            }
            String[] parts = input.split("\\s+");
            String cmd = parts[0].toLowerCase();
            String[] complement = Arrays.copyOfRange(parts, 1, parts.length);
            try {
                executeCommand(cmd, complement);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void loadCommands() {
        commands.put("add", TaskController::addTask);
        commands.put("list", TaskController::listTask);
        commands.put("update", TaskController::updateTask);
        commands.put("mark", TaskController::markTask);
        commands.put("delete", TaskController::deleteTask);
        commands.put("exit", args -> exit());
    }

    private static void exit() {
        System.out.println("Saliendo...");
        System.exit(0);
    }

    private static void executeCommand(String command, String[] complement) {
        Consumer<String[]> action = commands.get(command);
        if (action == null) throw new CommandNotFoundException("Error: El comando no existe: " + command);
        action.accept(complement);
    }
}
