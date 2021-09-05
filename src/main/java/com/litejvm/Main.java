package com.litejvm;

import com.litejvm.classpath.Classpath;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ClassLoader;
import com.litejvm.rtdata.heap.Method;

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
        // java.lang.Object => java/lang/Object
        String formattedClassName = args.getMainClass().replace(".", "/");

        ClassLoader classLoader = new ClassLoader(classpath);
        Class mainClass = classLoader.loadClass(formattedClassName);
        Method mainMethod = mainClass.getMainMethod();

        if (mainMethod != null) {
            new Interpreter().interpret(mainMethod);
        } else {
            System.out.printf("Main method not found in class %s\n", args.getMainClass());
        }
    }
}
