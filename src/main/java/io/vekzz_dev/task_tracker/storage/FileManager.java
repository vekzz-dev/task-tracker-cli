package io.vekzz_dev.task_tracker.storage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManager {

    private final Path basePath;
    private final Path filePath;

    public FileManager() {
        this(Path.of(System.getProperty("user.home")));
    }

    public FileManager(Path basePath) {
        this.basePath = basePath;
        this.filePath = basePath.resolve(".tktdata").resolve("tasks.json");
    }

    private void init() {
        try {
            Files.createDirectories(filePath.getParent());

            if (Files.notExists(filePath)) {
                Files.writeString(
                        filePath,
                        "{}",
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE
                );
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to initialize the file system", e);
        }
    }

    public void write(String content) {
        init();

        try {
            Files.writeString(
                    filePath,
                    content,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write file: " + filePath, e);
        }
    }

    public String read() {
        init();

        try {
            return Files.readString(
                    filePath,
                    StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read file: " + filePath, e);
        }
    }
}