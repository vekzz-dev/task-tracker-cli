package io.vekzz_dev.task_tracker.cli;

import io.vekzz_dev.task_tracker.services.TaskService;
import io.vekzz_dev.task_tracker.utils.TaskServiceHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

public class CommandRouterTest {

    @Mock
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        TaskServiceHolder.setTaskService(taskService);
    }

    @Test
    void testProcessExecutesAddCommand() {
        CommandRouter router = new CommandRouter(new String[]{"add", "new task"});

        router.process();

        verify(taskService).add("new task");
    }

    @Test
    void testProcessExecutesUpdateCommand() {
        CommandRouter router = new CommandRouter(new String[]{"update", "1", "updated task"});

        router.process();

        verify(taskService).update(1, "updated task");
    }

    @Test
    void testProcessExecutesMarkCommand() {
        CommandRouter router = new CommandRouter(new String[]{"mark", "1", "done"});

        router.process();

        verify(taskService).mark(1, "done");
    }

    @Test
    void testProcessExecutesDeleteCommand() {
        CommandRouter router = new CommandRouter(new String[]{"delete", "1"});

        router.process();

        verify(taskService).delete(1);
    }

    @Test
    void testProcessExecutesListCommand() {
        CommandRouter router = new CommandRouter(new String[]{"list", "all"});

        router.process();

        verify(taskService).list("all");
    }

    @Test
    void testProcessExecutesHelpCommand() {
        CommandRouter router = new CommandRouter(new String[]{"help"});

        router.process(); // No taskService call
    }

    @Test
    void testProcessThrowsForEmptyArguments() {
        CommandRouter router = new CommandRouter(new String[]{});

        assertThatThrownBy(router::process)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Missing command");
    }

    @Test
    void testProcessThrowsForUnknownCommand() {
        CommandRouter router = new CommandRouter(new String[]{"unknown"});

        assertThatThrownBy(router::process)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown command: unknown");
    }
}