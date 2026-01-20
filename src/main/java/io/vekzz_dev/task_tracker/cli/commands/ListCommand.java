package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.cli.Command;
import io.vekzz_dev.task_tracker.models.Task;
import io.vekzz_dev.task_tracker.output.OutputPrinter;
import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import io.vekzz_dev.task_tracker.validations.ArgumentValidator;

import java.util.List;

public class ListCommand implements Command {

    private final List<String> arguments;
    private final TaskService taskService;

    public ListCommand(List<String> arguments) {
        this.arguments = arguments;
        this.taskService = TaskServiceHolder.getTaskService();
    }

    @Override
    public void execute() {
        ArgumentValidator.validateArgumentSize(arguments, 1,
                "A status is the only required argument");

        String status = arguments.isEmpty() ? "all" : arguments.getFirst();

        List<Task> tasks = taskService.list(status);

        if (tasks.isEmpty()) throw new IllegalArgumentException("No tasks found");

        OutputPrinter.printTable(tasks);
    }
}