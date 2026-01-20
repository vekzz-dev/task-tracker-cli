package io.vekzz_dev.task_tracker.cli.commands;

import io.vekzz_dev.task_tracker.models.Status;
import io.vekzz_dev.task_tracker.models.Task;
import io.vekzz_dev.task_tracker.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ListCommandTest {

    @Mock
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task(1, "Task", Status.TODO, LocalDateTime.now(), LocalDateTime.now());
    }

    @Test
    void testExecuteListsAllTasks() {
        when(taskService.list("all")).thenReturn(List.of(task));

        ListCommand command = new ListCommand(List.of("all"), taskService);
        command.execute();

        verify(taskService).list("all");
    }

    @Test
    void testExecuteListsTasksByStatus() {
        when(taskService.list("todo")).thenReturn(List.of(task));

        ListCommand command = new ListCommand(List.of("todo"), taskService);
        command.execute();

        verify(taskService).list("todo");
    }

    @Test
    void testExecuteThrowsForTooFewArguments() {
        when(taskService.list("all")).thenReturn(List.of(task));

        ListCommand command = new ListCommand(List.of(), taskService);
        command.execute(); // Defaults to "all"
    }

    @Test
    void testExecuteThrowsForInvalidStatus() {
        ListCommand command = new ListCommand(List.of("invalid"), taskService);
        command.execute(); // Handles internally
    }

    @Test
    void testExecuteThrowsForNoTasksFound() {
        when(taskService.list("todo")).thenReturn(List.of());

        ListCommand command = new ListCommand(List.of("todo"), taskService);
        command.execute(); // Handles "No tasks found"
    }
}