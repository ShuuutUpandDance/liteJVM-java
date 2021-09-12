package com.litejvm.rtdata.heap.ref;

import com.litejvm.classfile.constant.ConstantMethodRefInfo;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;

public class MethodRef extends MemberRef{
    public Method method;

    public MethodRef(ConstantPool constantPool, ConstantMethodRefInfo methodRefInfo) {
        this.constantPool = constantPool;
        copyMemberRefInfo(methodRefInfo);
    }

    public Method resolvedMethod() {
        if (method == null) {
            resolveMethodRef();
        }
        return method;
    }

    private void resolveMethodRef() {
        Class d = this.constantPool.clazz;
        Class c = this.resolvedClass();

        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        Method method = lookupMethod(c, this.name, this.descriptor);

        if (method == null) {
            throw new NoSuchMethodError();
        }

        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }

        this.method = method;
    }

    private Method lookupMethod(Class clazz, String name, String descriptor) {
        Method method = MethodRefUtils.lookupMethodInClass(clazz, name, descriptor);
        if (method == null) {
            method = MethodRefUtils.lookupMethodInInterfaces(clazz.interfaces, name, descriptor);
        }
        return method;
    }
}
