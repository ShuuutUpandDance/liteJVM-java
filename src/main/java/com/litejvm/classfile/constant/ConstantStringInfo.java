package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassReader;

public class ConstantStringInfo extends AbstractConstantInfo{
    private final ConstantPool constantPool;

    private int stringIndex;

    public ConstantStringInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
    }

    public String getContent() {
        return this.constantPool.getUTF8(this.stringIndex);
    }
}
