package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTable extends AbstractAttributeInfo{
    private List<LocalVariableTableEntry> localVariableTable;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16();
        localVariableTable = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            localVariableTable.add(new LocalVariableTableEntry(
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16(),
                    reader.readUint16()
                    ));
        }
    }

    public List<LocalVariableTableEntry> getLocalVariableTable() {
        return localVariableTable;
    }

    public static class LocalVariableTableEntry {
        private final int startPc;
        private final int length;
        private final int nameIndex;
        private final int signatureIndex;
        private final int index;

        public LocalVariableTableEntry(int startPc, int length, int nameIndex, int signatureIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.signatureIndex = signatureIndex;
            this.index = index;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLength() {
            return length;
        }

        public int getNameIndex() {
            return nameIndex;
        }

        public int getSignatureIndex() {
            return signatureIndex;
        }

        public int getIndex() {
            return index;
        }
    }
}
