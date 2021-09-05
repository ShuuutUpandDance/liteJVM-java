package com.litejvm.rtdata.heap.ref;

import com.litejvm.classfile.constant.ConstantMethodRefInfo;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;

public class MethodRef extends MemberRef{
    public Method method;

    public MethodRef(ConstantPool constantPool, ConstantMethodRefInfo methodRefInfo) {
        this.constantPool = constantPool;
        copyMemberRefInfo(methodRefInfo);
    }
}
