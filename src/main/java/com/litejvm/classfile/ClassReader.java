package com.litejvm.classfile;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassReader {
    private char[] hexContent;

    ClassReader(byte[] input) {
        this.hexContent = Hex.encodeHex(input);
    }

    public int readUint32() {
        ClassFile.Hex32 hex32 = readHex32();
        return Integer.parseInt(new String(hex32.getContent()), 16);
    }

    public int readUint16() {
        ClassFile.Hex16 hex16 = readHex16();
        return Integer.parseInt(new String(hex16.getContent()), 16);
    }

    public List<Integer> readUint16Table() {
        int length = readUint16();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            result.add(readUint16());
        }
        return result;
    }

    public int readUint8() {
        ClassFile.Hex8 hex8 = readHex8();
        return Integer.parseInt(new String(hex8.getContent()), 16);
    }

    public byte[] readBytes(int length) {
        char[] hex = readHex(length * 2);
        try {
            return Hex.decodeHex(hex);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    public char[] readHex(int length) {
        char[] tmp = Arrays.copyOf(this.hexContent, length);
        this.hexContent = Arrays.copyOfRange(this.hexContent, length, this.hexContent.length);
        return tmp;
    }

    public ClassFile.Hex64 readHex64() {
        char[] tmp = Arrays.copyOf(this.hexContent, 16);
        this.hexContent = Arrays.copyOfRange(this.hexContent, 16, this.hexContent.length);
        return new ClassFile.Hex64(tmp);
    }

    public ClassFile.Hex32 readHex32() {
        char[] tmp = Arrays.copyOf(this.hexContent, 8);
        this.hexContent = Arrays.copyOfRange(this.hexContent, 8, this.hexContent.length);
        return new ClassFile.Hex32(tmp);
    }

    public ClassFile.Hex16 readHex16() {
        char[] tmp = Arrays.copyOf(this.hexContent, 4);
        this.hexContent = Arrays.copyOfRange(this.hexContent, 4, this.hexContent.length);
        return new ClassFile.Hex16(tmp);
    }

    public ClassFile.Hex8 readHex8() {
        char[] tmp = Arrays.copyOf(this.hexContent, 2);
        this.hexContent = Arrays.copyOfRange(this.hexContent, 2, this.hexContent.length);
        return new ClassFile.Hex8(tmp);
    }
}
