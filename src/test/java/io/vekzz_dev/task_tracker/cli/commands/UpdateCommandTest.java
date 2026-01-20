package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class UpdateCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteUpdatesTaskWithValidIdAndDescription() {
        UpdateCommand command = new UpdateCommand(List.of("1", "updated task"), taskService);
        command.execute();

        verify(taskService).update(1, "updated task");
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        UpdateCommand command = new UpdateCommand(List.of("1"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 2 argument(s) but received 1 argument(s). ID and description are the only required.");
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        UpdateCommand command = new UpdateCommand(List.of("1", "desc", "extra"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Expected 2 argument(s) but received 3 argument(s). ID and description are the only required.");
    }

    @Test
    void testExecuteThrowsForInvalidId() {
        UpdateCommand command = new UpdateCommand(List.of("abc", "desc"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task id must be a valid integer");
    }

    @Test
    void testExecuteThrowsForInvalidDescription() {
        UpdateCommand command = new UpdateCommand(List.of("1", ""), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Task description must not be blank");
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        doThrow(new RuntimeException("Service error")).when(taskService).update(1, "desc");

        UpdateCommand command = new UpdateCommand(List.of("1", "desc"), taskService);
        assertThatThrownBy(command::execute)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Service error");

        verify(taskService).update(1, "desc");
    }
}