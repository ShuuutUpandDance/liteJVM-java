package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classfile.ClassReader;

import java.math.BigInteger;

public class ConstantLongInfo extends AbstractConstantInfo{
    private long value;

    @Override
    public void readInfo(ClassReader reader) {
        ClassFile.Hex64 hex64 = reader.readHex64();
        this.value = new BigInteger(new String(hex64.getContent()), 16).longValue();
    }

    public long getValue() {
        return value;
    }
}
