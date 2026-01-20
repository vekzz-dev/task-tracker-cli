package io.vekzz_dev.task_tracker.cli;

public enum CommandType {

    ADD_TASK("add"),
    UPDATE_TASK("update"),
    DELETE_TASK("delete"),
    LIST_TASK("list"),
    MARK_TASK("mark"),
    HELP("help");

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CommandType getCommandType(String name) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.getName().equals(name)) {
                return commandType;
            }
        }
        return null;
    }
}