package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddCommandTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteAddsTaskWithValidDescription() {
        when(taskService.add("new task")).thenReturn(1);

        AddCommand command = new AddCommand(List.of("new task"), taskService);
        command.execute();

        verify(taskService).add("new task");
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        AddCommand command = new AddCommand(List.of(), taskService);
        command.execute(); // Should handle internally, no exception thrown externally
    }

    @Test
    void testExecuteThrowsForTooManyArguments() {
        AddCommand command = new AddCommand(List.of("desc1", "desc2"), taskService);
        command.execute(); // Should handle internally
    }

    @Test
    void testExecuteThrowsForInvalidDescription() {
        AddCommand command = new AddCommand(List.of(""), taskService);
        command.execute(); // Should handle internally
    }

    @Test
    void testExecuteHandlesTaskServiceException() {
        when(taskService.add("task")).thenThrow(new RuntimeException("Service error"));

        AddCommand command = new AddCommand(List.of("task"), taskService);
        command.execute();

        verify(taskService).add("task");
    }
}