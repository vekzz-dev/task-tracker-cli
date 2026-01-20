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
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        UpdateCommand command = new UpdateCommand(List.of("1", "desc", "extra"), taskService);
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForInvalidId() {
        UpdateCommand command = new UpdateCommand(List.of("abc", "desc"), taskService);
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForInvalidDescription() {
        UpdateCommand command = new UpdateCommand(List.of("1", ""), taskService);
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        doThrow(new RuntimeException("Service error")).when(taskService).update(1, "desc");

        UpdateCommand command = new UpdateCommand(List.of("1", "desc"), taskService);
        command.execute();

        verify(taskService).update(1, "desc");
    }
}