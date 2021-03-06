package com.litejvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.List;

public class Args {
    @Parameter(names = {"-?", "-help"}, description = "print help message", order = 3, help = true)
    boolean helpFlag = false;

    @Parameter(names = "-version", description = "print version and exit", order = 2)
    boolean versionFlag = false;

    @Parameter(names = { "-cp", "-classpath" }, description = "classpath", order = 1)
    String classpath;

    @Parameter(names = "-Xjre", description = "path to jre directory", order = 4)
    String Xjre;

    @Parameter(names = "-verboseClass", order = 5)
    boolean verboseClassFlag = false;

    @Parameter(names = "-verboseInst", order = 6)
    boolean verboseInstFlag = false;

    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    String getMainClass() {
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty()
                ? mainClassAndArgs.get(0)
                : null;
    }

    List<String> getAppArgs() {
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1
                ? mainClassAndArgs.subList(1, mainClassAndArgs.size())
                : null;
    }

    static Args parse(String[] argv) {
        Args args = new Args();
        JCommander cmd = JCommander.newBuilder()
                .addObject(args)
                .build();

        try {
            cmd.parse(argv);
        } catch (ParameterException e) {
            throw new RuntimeException("Cannot parse commandline parameter", e);
        }

        return args;
    }
}
