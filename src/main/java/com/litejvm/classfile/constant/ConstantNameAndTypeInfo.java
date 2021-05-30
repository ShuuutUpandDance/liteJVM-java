package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassReader;

public class ConstantNameAndTypeInfo extends AbstractConstantInfo{
    private final ConstantPool constantPool;

    public ConstantNameAndTypeInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    private int nameIndex;
    private int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }

    public String getName() {
        return this.constantPool.getUTF8(this.nameIndex);
    }

    public String getDescriptor() {
        return this.constantPool.getUTF8(this.descriptorIndex);
    }
}
