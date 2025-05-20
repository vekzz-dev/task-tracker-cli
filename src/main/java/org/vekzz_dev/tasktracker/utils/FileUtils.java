package org.vekzz_dev.tasktracker.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void ensureFileExists(File file) {
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
