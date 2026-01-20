package io.vekzz_dev.task_tracker.cli;

import io.vekzz_dev.task_tracker.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CommandRouterTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessExecutesAddCommand() {
        CommandRouter router = new CommandRouter(new String[]{"add", "new task"}, taskService);

        router.process();

        verify(taskService).add("new task");
    }

    @Test
    void testProcessExecutesUpdateCommand() {
        CommandRouter router = new CommandRouter(new String[]{"update", "1", "updated task"}, taskService);

        router.process();

        verify(taskService).update(1, "updated task");
    }

    @Test
    void testProcessExecutesMarkCommand() {
        CommandRouter router = new CommandRouter(new String[]{"mark", "1", "done"}, taskService);

        router.process();

        verify(taskService).mark(1, "done");
    }

    @Test
    void testProcessExecutesDeleteCommand() {
        CommandRouter router = new CommandRouter(new String[]{"delete", "1"}, taskService);

        router.process();

        verify(taskService).delete(1);
    }

    @Test
    void testProcessExecutesListCommand() {
        when(taskService.list("all")).thenReturn(List.of());

        CommandRouter router = new CommandRouter(new String[]{"list", "all"}, taskService);

        assertThatThrownBy(router::process)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No tasks found");
    }

    @Test
    void testProcessExecutesHelpCommand() {
        CommandRouter router = new CommandRouter(new String[]{"help"}, taskService);

        router.process(); // No taskService call
    }

    @Test
    void testProcessThrowsForEmptyArguments() {
        CommandRouter router = new CommandRouter(new String[]{}, taskService);

        assertThatThrownBy(router::process)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Missing command");
    }

    @Test
    void testProcessThrowsForUnknownCommand() {
        CommandRouter router = new CommandRouter(new String[]{"unknown"}, taskService);

        assertThatThrownBy(router::process)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown command: unknown");
    }
}