package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.cli.Command;
import io.vekzz_dev.task_tracker.output.OutputPrinter;
import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import io.vekzz_dev.task_tracker.validations.ArgumentValidator;
import io.vekzz_dev.task_tracker.validations.TaskValidator;

import java.util.List;

public class DeleteCommand implements Command {

    private final List<String> arguments;
    private final TaskService taskService;

    public DeleteCommand(List<String> arguments) {
        this.arguments = arguments;
        this.taskService = TaskServiceHolder.getTaskService();
    }

    @Override
    public void execute() {
        try {
            ArgumentValidator.validateArgumentSize(arguments, 1,
                    "ID is the only required.");

            int id = TaskValidator.parseAndValidateId(arguments.getFirst());

            taskService.delete(id);
            OutputPrinter.printMessage("Task deleted successfully with ID: " + id);

        } catch (Exception e) {
            OutputPrinter.printMessage(e.getMessage());
        }
    }
}