package org.vekzz_dev.tasktracker.model;

public enum Status {
    DONE("hecho ✅"),
    TODO("pendiente 🟦"),
    IN_PROGRESS("en progreso 🟠");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
