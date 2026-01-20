package io.vekzz_dev.task_tracker.models;

public enum Status {
    TODO("To do", "todo"),
    IN_PROGRESS("In progress", "in-progress"),
    DONE("Done", "done");

    private final String name;
    private final String argument;

    Status(String name, String argument) {
        this.name = name;
        this.argument = argument;
    }

    public String getName() {
        return name;
    }

    public static Status fromArgument(String argument) {
        for (Status status : Status.values()) {

            if (status.argument.equals(argument)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown argument: " + argument);
    }
}