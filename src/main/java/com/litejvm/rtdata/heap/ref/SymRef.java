package com.litejvm.rtdata.heap.ref;

import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;

public class SymRef {
    public ConstantPool constantPool;
    public String className;
    public Class clazz;

    public Class resolvedClass() {
        if (clazz == null) {
            resolveClassRef();
        }
        return clazz;
    }

    private void resolveClassRef() {
        Class d = constantPool.clazz;
        Class c = d.classLoader.loadClass(className);
        if (!c.isAccessibleTo(d)) {
            throw new RuntimeException(new IllegalAccessException());
        }
        clazz = c;
    }
}
