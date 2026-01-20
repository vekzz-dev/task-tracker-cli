package io.vekzz_dev.task_tracker.cli;

import io.vekzz_dev.task_tracker.cli.commands.*;

import java.util.List;
import java.util.Map;

public class CommandRouter {
    private final String[] args;

    public CommandRouter(String[] args) {
        this.args = args;
    }

    private Map<CommandType, Command> setCommandMap(List<String> arguments) {
        List<String> argsList = arguments.subList(1, arguments.size());

        return Map.of(
                CommandType.ADD_TASK, new AddCommand(argsList),
                CommandType.UPDATE_TASK, new UpdateCommand(argsList),
                CommandType.MARK_TASK, new MarkCommand(argsList),
                CommandType.DELETE_TASK, new DeleteCommand(argsList),
                CommandType.LIST_TASK, new ListCommand(argsList),
                CommandType.HELP, new HelpCommand()
        );
    }

    public void process() {
        List<String> arguments = CommandParser.parseArguments(args);

        if (arguments.isEmpty()) throw new IllegalArgumentException("Missing command");

        CommandType commandType = CommandType.getCommandType(arguments.getFirst().toLowerCase());

        if (commandType == null) throw new IllegalArgumentException("Unknown command: " + arguments.getFirst());

        Command selectedCommand = setCommandMap(arguments).get(commandType);

        if (selectedCommand != null) selectedCommand.execute();
    }
}