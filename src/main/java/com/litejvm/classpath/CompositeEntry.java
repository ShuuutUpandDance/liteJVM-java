package com.litejvm.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CompositeEntry implements Entry{
    private final List<Entry> entries;

    public CompositeEntry(String compositePath) {
        String[] splitPaths = compositePath.split(File.pathSeparator);

        this.entries = new ArrayList<>();
        for (String path : splitPaths) {
            if (path.endsWith("*")) {
                // wildcard path
                this.entries.addAll(createEntriesFromWildcardPath(path));
            } else {
                this.entries.add(createEntry(path));
            }
        }
    }

    private List<Entry> createEntriesFromWildcardPath(String wildcardPath) {
        // remove the "*" at the end to get the root dir
        Path rootDir = Paths.get(wildcardPath.substring(0, wildcardPath.length() - 1));
        try {
            return Files.walk(rootDir)
                    .filter(path -> !Files.isDirectory(path)
                            && (path.toString().endsWith("jar") || path.toString().endsWith("JAR") || path.toString().endsWith("zip") || path.toString().endsWith("ZIP")))
                    .map(path -> new ZipEntry(path.toString())).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private Entry createEntry(String path) {
        if (path.endsWith("jar") || path.endsWith("JAR")
                || path.endsWith("zip") || path.endsWith("ZIP")) {
            return new ZipEntry(path);
        } else {
            return new DirEntry(path);
        }
    }

    @Override
    public byte[] readClass(String className) {
        for (Entry entry : entries) {
            byte[] result = entry.readClass(className);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return this.entries.stream().map(Object::toString).collect(Collectors.joining(";"));
    }
}
