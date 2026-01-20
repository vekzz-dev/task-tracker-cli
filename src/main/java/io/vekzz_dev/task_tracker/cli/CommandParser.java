package io.vekzz_dev.task_tracker.cli;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    public static List<String> parseArguments(String[] args) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < args.length) {
            String token = args[i];

            if (token.startsWith("\"") && token.endsWith("\"") && token.length() > 1) {
                result.add(token.substring(1, token.length() - 1));
                i++;

            } else if (token.startsWith("\"")) {
                StringBuilder sb = new StringBuilder(token.substring(1));
                i++;

                while (i < args.length) {
                    String next = args[i];

                    if (next.endsWith("\"")) {
                        sb.append(" ").append(next.substring(0, next.length() - 1));
                        result.add(sb.toString());
                        i++;
                        break;

                    } else {
                        sb.append(" ").append(next);
                        i++;
                    }
                }
                if (i == args.length) {
                    throw new IllegalArgumentException("Unclosed quote in arguments");
                }
            } else {
                result.add(token);
                i++;
            }
        }
        return result;
    }
}