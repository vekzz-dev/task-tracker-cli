package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.cli.Command;
import io.vekzz_dev.task_tracker.output.OutputPrinter;
import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.validations.ArgumentValidator;
import io.vekzz_dev.task_tracker.validations.TaskValidator;

import java.util.List;

public class AddCommand implements Command {

    private final List<String> arguments;
    private final TaskService taskService;

    public AddCommand(List<String> arguments, TaskService taskService) {
        this.arguments = arguments;
        this.taskService = taskService;
    }

    @Override
    public void execute() {
        ArgumentValidator.validateArgumentSize(arguments, 1,
                "A description is the only required.");

        String description = arguments.getFirst();
        TaskValidator.validateDescription(description);

        int id = taskService.add(description);
        OutputPrinter.printMessage("Task added successfully. ID: " + id);
    }
}