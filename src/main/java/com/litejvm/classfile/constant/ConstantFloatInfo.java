package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classfile.ClassReader;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ConstantFloatInfo extends AbstractConstantInfo{
    private float value;

    @Override
    public void readInfo(ClassReader reader) {
        ClassFile.Hex32 hex32 = reader.readHex32();
        byte[] bytes;
        try {
            bytes = Hex.decodeHex(hex32.getContent());
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
        this.value = ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
    }

    public float getValue() {
        return value;
    }
}
