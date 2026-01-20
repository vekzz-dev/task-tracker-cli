package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class UpdateCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TaskServiceHolder.setTaskService(taskService);
    }

    @Test
    void testExecuteUpdatesTaskWithValidIdAndDescription() {
        UpdateCommand command = new UpdateCommand(List.of("1", "updated task"));
        command.execute();

        verify(taskService).update(1, "updated task");
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        UpdateCommand command = new UpdateCommand(List.of("1"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        UpdateCommand command = new UpdateCommand(List.of("1", "desc", "extra"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForInvalidId() {
        UpdateCommand command = new UpdateCommand(List.of("abc", "desc"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForInvalidDescription() {
        UpdateCommand command = new UpdateCommand(List.of("1", ""));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        doThrow(new RuntimeException("Service error")).when(taskService).update(1, "desc");

        UpdateCommand command = new UpdateCommand(List.of("1", "desc"));
        command.execute();

        verify(taskService).update(1, "desc");
    }
}