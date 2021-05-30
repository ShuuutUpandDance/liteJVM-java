package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;
import com.litejvm.classfile.constant.ConstantPool;

public class SourceFileAttribute extends AbstractAttributeInfo{
    private final ConstantPool constantPool;

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    private int sourceFileIndex;

    @Override
    public void readInfo(ClassReader reader) {
         this.sourceFileIndex = reader.readUint16();
    }

    public String getFileName() {
        return this.constantPool.getUTF8(sourceFileIndex);
    }
}
