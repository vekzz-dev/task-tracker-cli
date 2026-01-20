package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.cli.Command;
import io.vekzz_dev.task_tracker.output.OutputPrinter;
import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import io.vekzz_dev.task_tracker.validations.ArgumentValidator;
import io.vekzz_dev.task_tracker.validations.TaskValidator;

import java.util.List;

public class UpdateCommand implements Command {

    private final List<String> arguments;
    private final TaskService taskService;

    public UpdateCommand(List<String> arguments) {
        this.arguments = arguments;
        taskService = TaskServiceHolder.getTaskService();
    }

    @Override
    public void execute() {
        ArgumentValidator.validateArgumentSize(arguments, 2,
                "ID and description are the only required.");

        int id = TaskValidator.parseAndValidateId(arguments.getFirst());
        String description = arguments.get(1);
        TaskValidator.validateDescription(description);

        taskService.update(id, description);
        OutputPrinter.printMessage("Task updated successfully with ID: " + id);
    }
}