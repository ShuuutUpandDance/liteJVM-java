package com.litejvm.classpath;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipFile;

public class ZipEntry implements Entry {
    private final String absDir;

    public ZipEntry(String path) {
        this.absDir = Paths.get(path).toAbsolutePath().toString();
    }

    @Override
    public byte[] readClass(String className) {
        try  {
            ZipFile zipFile = new ZipFile(absDir);
            List<? extends java.util.zip.ZipEntry> matchedEntries = zipFile.stream().filter(zipEntry -> zipEntry.toString().endsWith(className)).collect(Collectors.toList());

            if (matchedEntries.isEmpty()) {
                return null;
            } else if (matchedEntries.size() > 1) {
                throw new RuntimeException(String.format("Found more than one class files with name [%s] from zip file [%s]", className, absDir));
            } else {
                try (InputStream inputStream = zipFile.getInputStream(matchedEntries.get(0))) {
                    return IOUtils.toByteArray(inputStream);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("Cannot read bytes from zipped file [%s]", absDir), e);
        }
    }

    @Override
    public String toString() {
        return this.absDir;
    }
}
