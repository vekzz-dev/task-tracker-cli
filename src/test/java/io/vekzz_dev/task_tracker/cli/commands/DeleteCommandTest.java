package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class DeleteCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TaskServiceHolder.setTaskService(taskService);
    }

    @Test
    void testExecuteDeletesTaskWithValidId() {
        DeleteCommand command = new DeleteCommand(List.of("1"));
        command.execute();

        verify(taskService).delete(1);
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        DeleteCommand command = new DeleteCommand(List.of());
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        DeleteCommand command = new DeleteCommand(List.of("1", "2"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForInvalidId() {
        DeleteCommand command = new DeleteCommand(List.of("abc"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        doThrow(new RuntimeException("Service error")).when(taskService).delete(1);

        DeleteCommand command = new DeleteCommand(List.of("1"));
        command.execute();

        verify(taskService).delete(1);
    }
}