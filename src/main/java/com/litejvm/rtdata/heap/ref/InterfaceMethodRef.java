package com.litejvm.rtdata.heap.ref;

import com.litejvm.classfile.constant.ConstantInterfaceMethodRefInfo;
import com.litejvm.rtdata.heap.ConstantPool;
import com.litejvm.rtdata.heap.Method;

public class InterfaceMethodRef extends MemberRef{
    Method method;

    public InterfaceMethodRef(ConstantPool constantPool, ConstantInterfaceMethodRefInfo interfaceMethodRefInfo) {
        this.constantPool = constantPool;
        copyMemberRefInfo(interfaceMethodRefInfo);
    }
}
