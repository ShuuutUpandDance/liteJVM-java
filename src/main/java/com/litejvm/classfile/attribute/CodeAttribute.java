package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;
import com.litejvm.classfile.constant.ConstantPool;

import java.util.ArrayList;
import java.util.List;

public class CodeAttribute extends AbstractAttributeInfo {
    private final ConstantPool constantPool;

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    private int maxStack;
    private int maxLocals;
    private byte[] code;
    private List<ExceptionTableEntry> exceptionTable;
    private List<AttributeInfo> attributes;

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();

        int codeByteLen = reader.readUint32();
        this.code = reader.readBytes(codeByteLen);
        this.exceptionTable = readExceptionTable(reader);
        this.attributes = readAttributes(reader, this.constantPool);
    }

    private List<ExceptionTableEntry> readExceptionTable(ClassReader reader) {
        int length = reader.readUint16();
        List<ExceptionTableEntry> result = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            result.add(new ExceptionTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16()
                    ));
        }

        return result;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public List<ExceptionTableEntry> getExceptionTable() {
        return exceptionTable;
    }

    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public static class ExceptionTableEntry {
        private final int startPc;
        private final int endPc;
        private final int handlerPc;
        private final int catchType;

        public ExceptionTableEntry(int startPc, int endPc, int handlerPc, int catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlerPc = handlerPc;
            this.catchType = catchType;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }
    }
}
