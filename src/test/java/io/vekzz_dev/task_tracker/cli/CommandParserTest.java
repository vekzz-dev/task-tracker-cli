package io.vekzz_dev.task_tracker.cli;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CommandParserTest {

    @Test
    void testParseArgumentsWithNoQuotes() {
        String[] args = {"arg1", "arg2", "arg3"};

        List<String> result = CommandParser.parseArguments(args);

        assertThat(result).containsExactly("arg1", "arg2", "arg3");
    }

    @Test
    void testParseArgumentsWithSingleQuotedArgument() {
        String[] args = {"\"arg1\"", "arg2"};

        List<String> result = CommandParser.parseArguments(args);

        assertThat(result).containsExactly("arg1", "arg2");
    }

    @Test
    void testParseArgumentsWithMultiWordQuotedArgument() {
        String[] args = {"\"arg1 arg2\"", "arg3"};

        List<String> result = CommandParser.parseArguments(args);

        assertThat(result).containsExactly("arg1 arg2", "arg3");
    }

    @Test
    void testParseArgumentsWithMixedQuotesAndNoQuotes() {
        String[] args = {"arg1", "\"arg2 arg3\"", "arg4"};

        List<String> result = CommandParser.parseArguments(args);

        assertThat(result).containsExactly("arg1", "arg2 arg3", "arg4");
    }

    @Test
    void testParseArgumentsWithEmptyArray() {
        String[] args = {};

        List<String> result = CommandParser.parseArguments(args);

        assertThat(result).isEmpty();
    }

    @Test
    void testParseArgumentsThrowsForUnclosedQuote() {
        String[] args = {"\"arg1"};

        assertThatThrownBy(() -> CommandParser.parseArguments(args))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unclosed quote in arguments");
    }

    @Test
    void testParseArgumentsWithMultipleQuotedArguments() {
        String[] args = {"\"arg1 arg2\"", "\"arg3 arg4\"", "arg5"};

        List<String> result = CommandParser.parseArguments(args);

        assertThat(result).containsExactly("arg1 arg2", "arg3 arg4", "arg5");
    }
}