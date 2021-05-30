package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassReader;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool {
    private Integer constantCount;
    private List<ConstantInfo> constantInfo;

    private ConstantPool(){}

    public static ConstantPool readConstantPool(ClassReader reader) {
        ConstantPool constantPool = new ConstantPool();
        constantPool.constantCount = Integer.parseInt(new String(reader.readHex16().getContent()), 16);
        constantPool.constantInfo = new ArrayList<>();
        constantPool.constantInfo.add(null);
        for (int i = 1; i < constantPool.constantCount; i++) {
            ConstantInfo constantInfo = AbstractConstantInfo.readConstantInfo(reader, constantPool);
            constantPool.constantInfo.add(constantInfo);
            if (constantInfo instanceof ConstantLongInfo || constantInfo instanceof ConstantDoubleInfo) {
                i++;
                constantPool.constantInfo.add(null);
            }
        }
        return constantPool;
    }

    public String getUTF8(int index) {
        ConstantUTF8Info constantUTF8Info = (ConstantUTF8Info) this.constantInfo.get(index);
        return constantUTF8Info.getContent();
    }

    public List<ConstantInfo> getConstantInfo() {
        return constantInfo;
    }
}
