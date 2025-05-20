package org.vekzz_dev.tasktracker.repository;

import org.vekzz_dev.tasktracker.model.Task;
import org.vekzz_dev.tasktracker.utils.FileUtils;
import org.vekzz_dev.tasktracker.utils.JsonParser;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileTaskRepository implements TaskRepository {
    private static final String HOME = System.getProperty("user.home");
    private static final Path DATA = Paths.get(HOME, "task-tracker", "data.json");
    private final File file;

    public FileTaskRepository() {
        this.file = DATA.toFile();
        FileUtils.ensureFileExists(file);
    }

    @Override
    public void save(List<Task> taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(JsonParser.convertToJson(taskList));
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
        return JsonParser.convertToTaskList(builder.toString());
    }
}
