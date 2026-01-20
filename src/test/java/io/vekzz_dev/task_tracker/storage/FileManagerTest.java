package io.vekzz_dev.task_tracker.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileManagerTest {

    @TempDir
    Path tempDir;

    @Test
    void testWriteCreatesDirectoriesAndFile() throws Exception {
        FileManager fm = new FileManager(tempDir);
        fm.write("");

        Path expectedFile = tempDir.resolve(".tktdata").resolve("tasks.json");
        assertThat(expectedFile).exists();
        assertThat(Files.readString(expectedFile)).isEmpty();
    }

    @Test
    void testReadDoesNotOverwriteExistingFile() throws Exception {
        Path dataDir = tempDir.resolve(".tktdata");
        Path tasksFile = dataDir.resolve("tasks.json");
        Files.createDirectories(dataDir);
        Files.writeString(tasksFile, "existing content", StandardOpenOption.CREATE);

        FileManager fm = new FileManager(tempDir);
        fm.read();

        assertThat(Files.readString(tasksFile)).isEqualTo("existing content");
    }

    @Test
    void testWriteAndReadContent() {
        FileManager fm = new FileManager(tempDir);
        fm.write("test content");

        assertThat(fm.read()).isEqualTo("test content");
    }

    @Test
    void testWriteOverwritesExistingContent() {
        FileManager fm = new FileManager(tempDir);
        fm.write("old content");
        fm.write("new content");

        assertThat(fm.read()).isEqualTo("new content");
    }

    @Test
    void testConstructorWithNullBasePath() {
        assertThatThrownBy(() -> new FileManager(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    void testWriteNullContent() {
        FileManager fm = new FileManager(tempDir);

        assertThatThrownBy(() -> fm.write(null)).isInstanceOf(NullPointerException.class);
    }

}