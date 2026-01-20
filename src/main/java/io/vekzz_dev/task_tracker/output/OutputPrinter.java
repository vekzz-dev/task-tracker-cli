package io.vekzz_dev.task_tracker.output;

import de.vandermeer.asciitable.AsciiTable;
import io.vekzz_dev.task_tracker.models.Task;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OutputPrinter {

    public static void printMessage(String message) {
        IO.println(message);
    }

    public static void printTable(List<Task> tasks) {
        AsciiTable table = new AsciiTable();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

        table.addRule();
        table.addRow("ID", "Description", "Status", "Created at", "Updated at");
        table.addRule();

        tasks.forEach(task -> {
            table.addRow(
                    task.getId(),
                    task.getDescription(),
                    task.getStatus().getName(),
                    formatter.format(task.getCreatedAt()),
                    formatter.format(task.getUpdatedAt())
            );
            table.addRule();
        });

        IO.println(table.render());
    }
}