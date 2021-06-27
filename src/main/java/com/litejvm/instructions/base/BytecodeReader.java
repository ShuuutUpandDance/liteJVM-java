package com.litejvm.instructions.base;

import java.math.BigInteger;

public class BytecodeReader {
    private byte[] code;
    private int pc;

    public BytecodeReader() {
    }

    public BytecodeReader(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public int readUint8() {
        byte b = code[pc++];
        return Byte.toUnsignedInt(b);
    }

    public int readInt8() {
        return code[pc++];
    }

    public int readUint16() {
        int i1 = readUint8();
        int i2 = readUint8();
        return (i1 << 8) | i2;
    }

    public int readInt16() {
        byte b1 = code[pc++];
        byte b2 = code[pc++];
        return new BigInteger(new byte[]{b1, b2}).intValueExact();
    }

    public long readUint32() {
        long i1 = readUint8();
        long i2 = readUint8();
        long i3 = readUint8();
        long i4 = readUint8();
        return (i1 << 24) | (i2 << 16) | (i3 << 8) | i4;
    }

    public int readInt32() {
        byte b1 = code[pc++];
        byte b2 = code[pc++];
        byte b3 = code[pc++];
        byte b4 = code[pc++];
        return new BigInteger(new byte[]{b1, b2, b3, b4}).intValueExact();
    }

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            readUint8();
        }
    }

    public int[] readInt32s(int jumpOffsetsCount) {
        int[] result = new int[jumpOffsetsCount];
        for (int i = 0; i < result.length; i++) {
            result[i] = readInt32();
        }
        return result;
    }

    public int getPC() {
        return pc;
    }
}
