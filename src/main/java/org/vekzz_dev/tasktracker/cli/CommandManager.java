package org.vekzz_dev.tasktracker.cli;

import org.vekzz_dev.tasktracker.controller.TaskController;
import org.vekzz_dev.tasktracker.exception.CommandNotFoundException;

import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {
    private final TaskController controller;

    private static final Map<String, Consumer<String[]>> commands = new HashMap<>();

    public CommandManager(TaskController controller) {
        this.controller = controller;
    }

    public void run() {
        loadCommands();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("task-tracker>> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("No se proporcionó ningún comando");
                continue;
            }

            String[] parts = parseArgs(input);
            if (parts.length == 0) {
                System.out.println("No se pudo analizar el comando");
                continue;
            }
            String cmd = parts[0].toLowerCase();
            String[] complement = Arrays.copyOfRange(parts, 1, parts.length);
            try {
                executeCommand(cmd, complement);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void loadCommands() {
        commands.put("add", controller::addTask);
        commands.put("list", controller::listTask);
        commands.put("update", controller::updateTask);
        commands.put("mark", controller::markTask);
        commands.put("delete", controller::deleteTask);
        commands.put("exit", args -> exit());
        commands.put("help", args -> printHelp());
    }

    private void exit() {
        System.out.println("Saliendo...");
        System.exit(0);
    }

    private static String[] parseArgs(String input) {
        List<String> tokens = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(input);
        while (m.find()) {
            if (m.group(1) != null) {
                tokens.add(m.group(1));
            } else {
                tokens.add(m.group(2));
            }
        }
        return tokens.toArray(new String[0]);
    }

    private void executeCommand(String command, String[] complement) {
        Consumer<String[]> action = commands.get(command);
        if (action == null) throw new CommandNotFoundException("Error: El comando no existe: " + command);
        action.accept(complement);
    }

    private void printHelp() {
        System.out.println("""
                Comandos disponibles:
                add <descripción>               -> Registrar tarea.
                update <id> <nueva descripción> -> Actualizar tarea.
                delete <id>                     -> Eliminar tarea.
                list <estado>                   -> Listar todas las tareas o por estado (done, todo, in-progress).
                mark <id> <estado>              -> Marcar tarea (done, todo, in-progress).
                help                            -> Ver información de comandos.
                exit                            -> Salir.
                """
        );
    }
}
