package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;

public class UnparsedAttribute extends AbstractAttributeInfo{
    private final String name;
    private final int length;
    private byte[] info;

    public UnparsedAttribute(String name, int length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes(length);
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public byte[] getInfo() {
        return info;
    }
}
