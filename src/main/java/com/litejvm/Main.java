package com.litejvm;

import com.beust.jcommander.Strings;
import com.litejvm.classpath.Classpath;

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

        Classpath classpath = Classpath.parse(args.Xjre, args.classpath);
        if (!Strings.isStringEmpty(args.getMainClass())) {
            // java.lang.Object => java/lang/Object
            String formattedClassName = args.getMainClass().replace(".", "/");
            byte[] bytes = classpath.readClass(formattedClassName);
            if (bytes != null) {
                System.out.printf("Class data: %s\n", new String(bytes));
            } else {
                System.out.printf("Cannot load given class: %s\n", args.getMainClass());
            }
        }
    }
}
