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

public class DeleteCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteDeletesTaskWithValidId() {
        DeleteCommand command = new DeleteCommand(List.of("1"), taskService);
        command.execute();

        verify(taskService).delete(1);
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        DeleteCommand command = new DeleteCommand(List.of(), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 1 argument(s) but received 0 argument(s). ID is the only required.");
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        DeleteCommand command = new DeleteCommand(List.of("1", "2"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 1 argument(s) but received 2 argument(s). ID is the only required.");
    }

    @Test
    void testExecuteThrowsForInvalidId() {
        DeleteCommand command = new DeleteCommand(List.of("abc"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task id must be a valid integer");
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        doThrow(new RuntimeException("Service error")).when(taskService).delete(1);

        DeleteCommand command = new DeleteCommand(List.of("1"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Service error");

        verify(taskService).delete(1);
    }
}