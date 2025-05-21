package org.vekzz_dev.tasktracker.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static void ensureFileExists(File file) {
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                if (!parentDir.mkdirs()) throw new IOException("The directory could not be created: " + parentDir);
            }
            if (!file.exists()) {
                if (!file.createNewFile()) throw new IOException("The file could not be created: " + file);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error creating the file: " + e.getMessage());
        }
    }
}
