package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.cli.Command;
import io.vekzz_dev.task_tracker.output.OutputPrinter;
import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.validations.ArgumentValidator;
import io.vekzz_dev.task_tracker.validations.TaskValidator;

import java.util.List;

public class MarkCommand implements Command {

    private final List<String> arguments;
    private final TaskService taskService;

    public MarkCommand(List<String> arguments, TaskService taskService) {
        this.arguments = arguments;
        this.taskService = taskService;
    }

    @Override
    public void execute() {
        ArgumentValidator.validateArgumentSize(arguments, 2,
                "ID and status are the only required.");

        int id = TaskValidator.parseAndValidateId(arguments.getFirst());
        String status = arguments.get(1);
        TaskValidator.validateDescription(status);

        taskService.mark(id, status);
        OutputPrinter.printMessage("Task marked successfully with ID: " + id);
    }
}