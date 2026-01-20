package io.vekzz_dev.task_tracker.cli.commands;

import org.junit.jupiter.api.Test;

public class HelpCommandTest {

    @Test
    void testExecutePrintsHelpMessage() {
        HelpCommand command = new HelpCommand();
        command.execute(); // Should print help without exceptions
    }
}