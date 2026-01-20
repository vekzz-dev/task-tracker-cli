package io.vekzz_dev.task_tracker.validations;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArgumentValidatorTest {

    @Test
    void testValidateArgumentSizePassesWithCorrectSize() {
        List<String> args = List.of("arg1", "arg2");

        assertThatCode(() -> ArgumentValidator.validateArgumentSize(args, 2, "Test message")).doesNotThrowAnyException();
    }

    @Test
    void testValidateArgumentSizeThrowsForTooFewArguments() {
        List<String> args = List.of("arg1");

        assertThatThrownBy(() -> ArgumentValidator.validateArgumentSize(args, 2, "Test message"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 2 argument(s) but received 1 argument(s). Test message");
    }

    @Test
    void testValidateArgumentSizeThrowsForTooManyArguments() {
        List<String> args = List.of("arg1", "arg2", "arg3");

        assertThatThrownBy(() -> ArgumentValidator.validateArgumentSize(args, 2, "Test message"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 2 argument(s) but received 3 argument(s). Test message");
    }

    @Test
    void testValidateArgumentSizeThrowsForNullArguments() {
        assertThatThrownBy(() -> ArgumentValidator.validateArgumentSize(null, 2, "Test message"))
                .isInstanceOf(NullPointerException.class);
    }
}