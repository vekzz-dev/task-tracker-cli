package io.vekzz_dev.task_tracker.validations;

import java.util.List;
import java.util.Objects;

public class ArgumentValidator {

    public static void validateArgumentSize(
            List<String> arguments,
            int expectedSize,
            String errorMessage) {

        Objects.requireNonNull(arguments, "arguments cannot be null");

        if (arguments.size() != expectedSize) {
            throw new IllegalArgumentException(
                    String.format(
                            "Expected %d argument(s) but received %d argument(s). %s",
                            expectedSize, arguments.size(), errorMessage
                    )
            );
        }
    }
}
