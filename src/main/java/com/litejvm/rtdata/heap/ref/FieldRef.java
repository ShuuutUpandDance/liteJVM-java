package com.litejvm.rtdata.heap.ref;

import com.litejvm.classfile.constant.ConstantFieldRefInfo;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Field;

public class FieldRef extends MemberRef{
    Field field;

    public FieldRef(ConstantPool constantPool, ConstantFieldRefInfo fieldRefInfo) {
        this.constantPool = constantPool;
        copyMemberRefInfo(fieldRefInfo);
    }

    public Field resolvedField() {
        if (field == null) {
            resolveFieldRef();
        }
        return field;
    }

    private void resolveFieldRef() {
        Class d = constantPool.clazz;;
        Class c = resolvedClass();
        Field field = lookupField(c, name, descriptor);
        if (field == null) {
            throw new RuntimeException(new NoSuchFieldError());
        }
        if (!field.isAccessibleTo(d)) {
            throw new RuntimeException(new IllegalAccessError());
        }
        this.field = field;
    }

    private Field lookupField(Class clazz, String name, String descriptor) {
        for (Field field : clazz.fields) {
            if (name.equals(field.name) && descriptor.equals(field.descriptor)) {
                return field;
            }
        }

        for (Class anInterface : clazz.interfaces) {
            return lookupField(anInterface, name, descriptor);
        }

        if (clazz.superClass != null) {
            return lookupField(clazz.superClass, name, descriptor);
        }

        return null;
    }
}
