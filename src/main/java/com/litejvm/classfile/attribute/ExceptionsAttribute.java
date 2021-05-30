package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;

import java.util.List;

public class ExceptionsAttribute extends AbstractAttributeInfo{
    private List<Integer> exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16Table();
    }

    public List<Integer> getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
