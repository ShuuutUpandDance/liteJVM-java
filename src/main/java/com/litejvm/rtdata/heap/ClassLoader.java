package com.litejvm.rtdata.heap;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classpath.Classpath;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ClassLoader {
    Classpath classpath;
    Map<String, Class> classMap;

    public ClassLoader(Classpath classpath) {
        this.classpath = classpath;
        this.classMap = new HashMap<>();
    }

    public Class loadClass(String name) {
        if (classMap.containsKey(name)) {
            return classMap.get(name);
        } else {
            return loadNonArrayClass(name);
        }
    }

    private Class loadNonArrayClass(String name) {
        byte[] bytes = readClass(name);
        Class clazz = defineClass(bytes);
        link(clazz);
        System.out.printf("[Loaded %s]\n", name);
        return clazz;
    }

    private byte[] readClass(String name) {
        byte[] result = classpath.readClass(name);
        if (result == null) {
            throw new RuntimeException(new ClassNotFoundException(name));
        }
        return result;
    }

    private void link(Class clazz) {
        verify(clazz);
        prepare(clazz);
    }

    private void verify(Class clazz) {
        // will not be implemented
    }

    private void prepare(Class clazz) {
        // TODO
        calcInstanceFieldSlotIds(clazz);
        calcStaticFieldSlotIds(clazz);
        allocAndInitStaticVars(clazz);
    }

    private void calcInstanceFieldSlotIds(Class clazz) {
        int slotId = 0;
        if (clazz.superClass != null) {
            slotId = clazz.superClass.instanceSlotCount;
        }
        for (Field field : clazz.fields) {
            if (!field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.instanceSlotCount = slotId;
    }

    private void calcStaticFieldSlotIds(Class clazz) {
        int slotId = 0;
        for (Field field : clazz.fields) {
            if (field.isStatic()) {
                field.slotId = slotId;
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        clazz.staticSlotCount = slotId;
    }

    private void allocAndInitStaticVars(Class clazz) {
        clazz.staticVars = new Slots(clazz.staticSlotCount);
        for (Field field : clazz.fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(clazz, field);
            }
        }
    }

    private void initStaticFinalVar(Class clazz, Field field) {
        Slots vars = clazz.staticVars;
        ConstantPool constantPool = clazz.constantPool;
        int constValIndex = field.constValueIndex;
        int slotId = field.slotId;

        if (constValIndex > 0) {
            switch (field.descriptor) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I": {
                    int val = (int) constantPool.getConstant(constValIndex);
                    vars.setInt(slotId, val);
                    break;
                }
                case "J": {
                    long val = (long) constantPool.getConstant(constValIndex);
                    vars.setLong(slotId, val);
                    break;
                }
                case "F": {
                    float val = (float) constantPool.getConstant(constValIndex);
                    vars.setFloat(slotId, val);
                    break;
                }
                case "D": {
                    double val = (double) constantPool.getConstant(constValIndex);
                    vars.setDouble(slotId, val);
                    break;
                }
                case "Ljava/lang/String;": {
                    throw new RuntimeException("TODO");
                }
            }
        }
    }

    private Class defineClass(byte[] bytes) {
        Class clazz = parseClass(bytes);
        clazz.classLoader = this;
        resolveSuperClass(clazz);
        resolveInterfaces(clazz);
        this.classMap.put(clazz.name, clazz);
        return clazz;
    }

    private Class parseClass(byte[] bytes) {
        ClassFile classFile = ClassFile.parse(bytes);
        return new Class(classFile);
    }

    private void resolveSuperClass(Class clazz) {
        if (!"java/lang/Object".equals(clazz.name)) {
            clazz.superClass = clazz.classLoader.loadClass(clazz.superClassName);
        }
    }

    private void resolveInterfaces(Class clazz) {
        if (!clazz.interfaceNames.isEmpty()) {
            clazz.interfaces = clazz.interfaceNames.stream()
                    .map(name -> clazz.classLoader.loadClass(name))
                    .collect(Collectors.toList());
        }
    }
}
