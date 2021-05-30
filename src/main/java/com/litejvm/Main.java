package com.litejvm;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classfile.MemberInfo;
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
        // java.lang.Object => java/lang/Object
        String formattedClassName = args.getMainClass().replace(".", "/");

        ClassFile classFile = loadClass(formattedClassName, classpath);
        printClassInfo(classFile);
    }

    static ClassFile loadClass(String className, Classpath classpath) {
        byte[] bytes = classpath.readClass(className);
        return ClassFile.parse(bytes);
    }

    static void printClassInfo(ClassFile classFile) {
        System.out.printf("version: %d.%d\n", classFile.getMajorVersion(), classFile.getMinorVersion());
        System.out.printf("constants count: %d\n", classFile.getConstantPool().getConstantInfo().size());
        System.out.printf("access flags: 0x%x\n", classFile.getAccessFlags());
        System.out.printf("this class: %s\n", classFile.getThisClassName());
        System.out.printf("super class: %s\n", classFile.getSuperClassName());
        System.out.printf("interfaces: %s\n", classFile.getInterfacesNames());
        System.out.printf("fields count: %d\n", classFile.getFields().size());
        for (MemberInfo field : classFile.getFields()) {
            System.out.printf(" %s\n", field.getName());
        }
        System.out.printf("methods count: %d\n", classFile.getMethods().size());
        for (MemberInfo method : classFile.getMethods()) {
            System.out.printf(" %s\n", method.getName());
        }
    }
}
