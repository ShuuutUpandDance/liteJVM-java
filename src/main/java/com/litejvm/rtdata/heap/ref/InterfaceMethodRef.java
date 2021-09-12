package com.litejvm.rtdata.heap.ref;

import com.litejvm.classfile.constant.ConstantInterfaceMethodRefInfo;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;

public class InterfaceMethodRef extends MemberRef {
    Method method;

    public InterfaceMethodRef(ConstantPool constantPool, ConstantInterfaceMethodRefInfo interfaceMethodRefInfo) {
        this.constantPool = constantPool;
        copyMemberRefInfo(interfaceMethodRefInfo);
    }

    public Method resolvedInterfaceMethod() {
        if (this.method == null) {
            resolveInterfaceMethodRef();
        }

        return this.method;
    }

    private void resolveInterfaceMethodRef() {
        Class d = this.constantPool.clazz;
        Class c = this.resolvedClass();

        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        Method method = lookupInterfaceMethod(c, this.name, this.descriptor);

        if (method == null) {
            throw new NoSuchMethodError();
        }

        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }

        this.method = method;
    }

    private Method lookupInterfaceMethod(Class clazz, String name, String descriptor) {
        for (Method method : clazz.methods) {
            if (name.equals(method.name) && descriptor.equals(method.descriptor)) {
                return method;
            }
        }
        return MethodRefUtils.lookupMethodInInterfaces(clazz.interfaces, name, descriptor);
    }
}
