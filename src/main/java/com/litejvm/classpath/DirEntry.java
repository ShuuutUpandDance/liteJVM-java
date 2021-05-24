package com.litejvm.classpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirEntry implements Entry{
    private final String absDir;

    public DirEntry(String path) {
        this.absDir = Paths.get(path).toAbsolutePath().toString();
    }

    @Override
    public byte[] readClass(String className) {
        Path clsFilePath = Paths.get(absDir, className);

        if (Files.notExists(clsFilePath)) {
            return null;
        }

        try {
            return Files.readAllBytes(clsFilePath);
        } catch (IOException e) {
            throw new RuntimeException(String.format("Cannot read bytes from class file %s", clsFilePath), e);
        }
    }

    @Override
    public String toString() {
        return this.absDir;
    }
}
