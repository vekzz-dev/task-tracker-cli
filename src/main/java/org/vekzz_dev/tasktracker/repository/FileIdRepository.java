package org.vekzz_dev.tasktracker.repository;

import org.vekzz_dev.tasktracker.utils.FileUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIdRepository implements IdRepository {
    private static final String HOME = System.getProperty("user.home");
    private static final Path BIN_DATA = Paths.get(HOME, "task-tracker", "id_counter.bin");
    private final File file;

    public FileIdRepository() {
        this.file = BIN_DATA.toFile();
        FileUtils.ensureFileExists(file);
    }

    @Override
    public int nextId() {
        int id = getId();
        id++;
        updateId(id);
        return id;
    }

    private void updateId(int id) {
        try (DataOutputStream dataOutput = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)))) {
            dataOutput.writeInt(id);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el id: " + e.getMessage());
        }
    }

    private int getId() {
        if (file.length() == 0) return 0;
        try (DataInputStream dataInput = new DataInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            return dataInput.readInt();
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el id: " + e.getMessage());
        }
    }
}
