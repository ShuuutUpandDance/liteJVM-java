package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classfile.ClassReader;

public class ConstantIntegerInfo extends AbstractConstantInfo {
    private int value;

    @Override
    public void readInfo(ClassReader reader) {
        ClassFile.Hex32 hex32 = reader.readHex32();
        this.value = Integer.parseInt(new String(hex32.getContent()), 16);
    }

    public int getValue() {
        return value;
    }
}
