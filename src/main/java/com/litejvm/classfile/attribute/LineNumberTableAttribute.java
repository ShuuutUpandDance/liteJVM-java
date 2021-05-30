package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;

import java.util.ArrayList;
import java.util.List;

public class LineNumberTableAttribute extends AbstractAttributeInfo{
    private List<LineNumberTableEntry> lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readUint16();

        lineNumberTable = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            lineNumberTable.add(new LineNumberTableEntry(
                    reader.readUint16(),
                    reader.readUint16()
            ));
        }
    }

    public List<LineNumberTableEntry> getLineNumberTable() {
        return lineNumberTable;
    }

    public static class LineNumberTableEntry {
        private final int startPc;
        private final int lineNumber;

        public LineNumberTableEntry(int startPc, int lineNumber) {
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }

        public int getStartPc() {
            return startPc;
        }

        public int getLineNumber() {
            return lineNumber;
        }
    }
}
