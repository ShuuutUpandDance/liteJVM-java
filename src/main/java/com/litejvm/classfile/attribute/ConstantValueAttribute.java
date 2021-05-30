package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;

public class ConstantValueAttribute extends AbstractAttributeInfo{
    private int constantValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
