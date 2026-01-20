package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.cli.Command;
import io.vekzz_dev.task_tracker.output.OutputPrinter;

public class HelpCommand implements Command {

    @Override
    public void execute() {
        OutputPrinter.printMessage("""
                Available commands:
                add <description>               -> Add new task.
                update <id> <new description>   -> Update existing task.
                delete <id>                     -> Delete task.
                list <status>                   -> List all tasks or by status (done, todo, in-progress).
                mark <id> <status>              -> Mark task (done, todo, in-progress).
                help                            -> View command information.
                exit                            -> Exit.
                """
        );
    }
}