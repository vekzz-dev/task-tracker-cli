package io.vekzz_dev.task_tracker.validations;

public class TaskValidator {

    public static int parseAndValidateId(String input) {
        int id;
        try {
            id = Integer.parseInt(input);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Task id must be a valid integer");
        }

        if (id <= 0) {
            throw new IllegalArgumentException("Task id must be a positive integer");
        }
        return id;
    }

    public static void validateDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description must not be blank");
        }
    }
}