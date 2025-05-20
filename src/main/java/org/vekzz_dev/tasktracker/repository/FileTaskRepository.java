package org.vekzz_dev.tasktracker.repository;

import org.vekzz_dev.tasktracker.model.Task;
import org.vekzz_dev.tasktracker.utils.JsonParser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileTaskRepository implements TaskRepository {
    private static final String HOME = System.getProperty("user.home");
    private static final Path DATA = Paths.get(HOME, "task-tracker", "data.json");
    private final File file;
    private final JsonParser parser;

    public FileTaskRepository(JsonParser parser) {
        this.parser = parser;
        this.file = DATA.toFile();
        ensureFileExists(file);
    }

    @Override
    public void save(List<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(parser.convertToJson(taskList));
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los datos: " + e.getMessage());
        }
    }

    @Override
    public List<Task> findAll() {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer los datos: " + e.getMessage());
        }
        return parser.convertToTaskList(builder.toString());
    }

    private void ensureFileExists(File file) {
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) throw new IOException("No se pudo crear el directorio: " + parentDir);
            }
            if (!file.exists()) {
                if (!file.createNewFile()) throw new IOException("No se pudo crear el archivo: " + file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el archivo: " + e.getMessage());
        }
    }
}
