package org.vekzz_dev.tasktracker.model;

public enum Status {
    DONE("done ✅"),
    TODO("to-do 🟦"),
    IN_PROGRESS("in progress 🟠");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
