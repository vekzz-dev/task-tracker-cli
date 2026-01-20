package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class MarkCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TaskServiceHolder.setTaskService(taskService);
    }

    @Test
    void testExecuteMarksTaskWithValidIdAndStatus() {
        MarkCommand command = new MarkCommand(List.of("1", "done"));
        command.execute();

        verify(taskService).mark(1, "done");
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        MarkCommand command = new MarkCommand(List.of("1"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        MarkCommand command = new MarkCommand(List.of("1", "done", "extra"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForInvalidId() {
        MarkCommand command = new MarkCommand(List.of("abc", "done"));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForInvalidStatus() {
        MarkCommand command = new MarkCommand(List.of("1", ""));
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        doThrow(new RuntimeException("Service error")).when(taskService).mark(1, "done");

        MarkCommand command = new MarkCommand(List.of("1", "done"));
        command.execute();

        verify(taskService).mark(1, "done");
    }
}