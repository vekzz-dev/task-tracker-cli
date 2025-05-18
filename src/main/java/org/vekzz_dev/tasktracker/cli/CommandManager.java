package org.vekzz_dev.tasktracker.cli;

import org.vekzz_dev.tasktracker.controller.TaskController;

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
        }
    }

    private static void loadCommands() {
        commands.put("add", TaskController::addTask);
        commands.put("list", TaskController::listTask);
        commands.put("update", TaskController::updateTask);
        commands.put("mark", TaskController::markTask);
        commands.put("delete", TaskController::deleteTask);
    }
}
