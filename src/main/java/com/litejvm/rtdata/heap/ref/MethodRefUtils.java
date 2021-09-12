package com.litejvm.rtdata.heap.ref;

import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.Method;

import java.util.List;

public class MethodRefUtils {
    public static Method lookupMethodInClass(Class clazz, String name, String descriptor) {
        for (Class c = clazz; c != null; c = c.superClass) {
            for (Method method : c.methods) {
                if (method.name.equals(name) && method.descriptor.equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }
    public static Method lookupMethodInInterfaces(List<Class> interfaces, String name, String descriptor) {
        for (Class anInterface : interfaces) {
            for (Method method : anInterface.methods) {
                if (name.equals(method.name) && descriptor.equals(method.descriptor)) {
                    return method;
                }
            }

            Method method = lookupMethodInInterfaces(anInterface.interfaces, name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }
}
