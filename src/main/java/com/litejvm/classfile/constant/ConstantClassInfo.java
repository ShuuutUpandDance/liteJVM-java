package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassReader;

public class ConstantClassInfo extends AbstractConstantInfo{
    private final ConstantPool constantPool;
    private int nameIndex;

    public ConstantClassInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
         this.nameIndex = reader.readUint16();
    }

    public String getName() {
        return this.constantPool.getUTF8(nameIndex);
    }
}
