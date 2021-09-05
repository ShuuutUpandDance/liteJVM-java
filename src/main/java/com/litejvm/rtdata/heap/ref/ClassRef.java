package com.litejvm.rtdata.heap.ref;

import com.litejvm.classfile.constant.ConstantClassInfo;
import com.litejvm.rtdata.heap.ConstantPool;

public class ClassRef extends SymRef{
    public ClassRef(ConstantPool constantPool, ConstantClassInfo classInfo) {
        this.constantPool = constantPool;
        this.className = classInfo.getName();
    }
}
