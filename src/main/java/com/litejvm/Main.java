package com.litejvm;

public class Main {
    public static void main(String[] args) {
        Args cliArgs = Args.parse(args);

        if (cliArgs.helpFlag || cliArgs.getMainClass() == null) {
            printUsage();
        } else if (cliArgs.versionFlag) {
            System.out.println("LiteJVM version 0.0.1");
        } else {
            startJVM(cliArgs);
        }
    }

    static void printUsage() {
        System.out.println("Usage: [-options] class [args...]\n");
    }

    static void startJVM(Args args) {
        System.out.printf("classpath:%s, class:%s, args:%s\n", args.classpath, args.getMainClass(), args.getAppArgs());
    }
}
