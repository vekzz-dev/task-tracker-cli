package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class MarkCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteMarksTaskWithValidIdAndStatus() {
        MarkCommand command = new MarkCommand(List.of("1", "done"), taskService);
        command.execute();

        verify(taskService).mark(1, "done");
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        MarkCommand command = new MarkCommand(List.of("1"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 2 argument(s) but received 1 argument(s). ID and status are the only required.");
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        MarkCommand command = new MarkCommand(List.of("1", "done", "extra"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 2 argument(s) but received 3 argument(s). ID and status are the only required.");
    }

    @Test
    void testExecuteThrowsForInvalidId() {
        MarkCommand command = new MarkCommand(List.of("abc", "done"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task id must be a valid integer");
    }

    @Test
    void testExecuteThrowsForInvalidStatus() {
        MarkCommand command = new MarkCommand(List.of("1", ""), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task description must not be blank");
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        doThrow(new RuntimeException("Service error")).when(taskService).mark(1, "done");

        MarkCommand command = new MarkCommand(List.of("1", "done"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Service error");

        verify(taskService).mark(1, "done");
    }
}