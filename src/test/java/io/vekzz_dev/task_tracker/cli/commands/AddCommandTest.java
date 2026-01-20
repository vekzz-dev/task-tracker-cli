package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

public class AddCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TaskServiceHolder.setTaskService(taskService);
    }

    @Test
    void testExecuteAddsTaskWithValidDescription() {
        when(taskService.add("new task")).thenReturn(1);

        AddCommand command = new AddCommand(List.of("new task"));
        command.execute();

        verify(taskService).add("new task");
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        AddCommand command = new AddCommand(List.of());
        command.execute(); // Should handle internally, no exception thrown externally
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        AddCommand command = new AddCommand(List.of("desc1", "desc2"));
        command.execute(); // Should handle internally
    }

    @Test
    void testExecuteThrowsForInvalidDescription() {
        AddCommand command = new AddCommand(List.of(""));
        command.execute(); // Should handle internally
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        when(taskService.add("task")).thenThrow(new RuntimeException("Service error"));

        AddCommand command = new AddCommand(List.of("task"));
        command.execute();

        verify(taskService).add("task");
    }
}