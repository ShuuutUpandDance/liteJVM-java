package com.litejvm.classpath;

import com.beust.jcommander.Strings;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Classpath implements Entry {
    private CompositeEntry bootClasspath;
    private CompositeEntry extClasspath;
    private CompositeEntry userClasspath;

    private Classpath() {}

    @Override
    public byte[] readClass(String className) {
        if (!className.endsWith(".class")) {
            className = className + ".class";
        }
        // Parental Delegation Model
        byte[] result = bootClasspath.readClass(className);
        if (result == null) {
            result = extClasspath.readClass(className);
        }
        if (result == null) {
            result = userClasspath.readClass(className);
        }
        return result;
    }

    @Override
    public String toString() {
        return userClasspath.toString();
    }

    public static Classpath parse(String jreOption, String cpOption) {
        Classpath classpath = new Classpath();
        classpath.bootClasspath = parseBootClasspath(jreOption);
        classpath.extClasspath = parseExtClasspath(jreOption);
        classpath.userClasspath = parseUserClasspath(cpOption);

        return classpath;
    }

    private static CompositeEntry parseUserClasspath(String cpOption) {
        if (Strings.isStringEmpty(cpOption)) {
            return new CompositeEntry(".");
        }
        return new CompositeEntry(cpOption);
    }

    private static CompositeEntry parseExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        return new CompositeEntry(String.join(File.separator, jreDir, "lib", "ext", "*"));
    }

    private static CompositeEntry parseBootClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        return new CompositeEntry(String.join(File.separator, jreDir, "lib", "*"));
    }

    private static String getJreDir(String jreOption) {
        if (!Strings.isStringEmpty(jreOption) && Files.exists(Paths.get(jreOption))) {
            return jreOption;
        }

        if (Files.exists(Paths.get("./jre"))) {
            return "./jre";
        }

        String javaHome = System.getProperty("java.home");
        if (!Strings.isStringEmpty(javaHome)) {
            return javaHome;
        }

        throw new RuntimeException("Cannot find jre folder");
    }
}
