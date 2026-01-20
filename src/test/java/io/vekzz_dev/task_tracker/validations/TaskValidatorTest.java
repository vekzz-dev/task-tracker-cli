package io.vekzz_dev.task_tracker.validations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class TaskValidatorTest {

    @Test
    void testParseAndValidateIdReturnsValidId() {
        int result = TaskValidator.parseAndValidateId("5");

        assertThat(result).isEqualTo(5);
    }

    @Test
    void testParseAndValidateIdThrowsForInvalidString() {
        assertThatThrownBy(() -> TaskValidator.parseAndValidateId("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task id must be a valid integer");
    }

    @Test
    void testParseAndValidateIdThrowsForZero() {
        assertThatThrownBy(() -> TaskValidator.parseAndValidateId("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task id must be a positive integer");
    }

    @Test
    void testParseAndValidateIdThrowsForNegative() {
        assertThatThrownBy(() -> TaskValidator.parseAndValidateId("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task id must be a positive integer");
    }

    @Test
    void testValidateDescriptionPassesForValid() {
        assertThatCode(() -> TaskValidator.validateDescription("Valid description")).doesNotThrowAnyException();
    }

    @Test
    void testValidateDescriptionThrowsForNull() {
        assertThatThrownBy(() -> TaskValidator.validateDescription(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task description must not be blank");
    }

    @Test
    void testValidateDescriptionThrowsForBlank() {
        assertThatThrownBy(() -> TaskValidator.validateDescription("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task description must not be blank");
    }
}