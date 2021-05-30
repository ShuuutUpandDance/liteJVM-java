package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classfile.ClassReader;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ConstantDoubleInfo extends AbstractConstantInfo{
    private double value;

    @Override
    public void readInfo(ClassReader reader) {
        ClassFile.Hex64 hex64 = reader.readHex64();
        byte[] bytes;
        try {
            bytes = Hex.decodeHex(hex64.getContent());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
        this.value = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getDouble();
    }

    public double getValue() {
        return value;
    }
}
