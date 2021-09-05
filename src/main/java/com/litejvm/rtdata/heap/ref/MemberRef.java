package com.litejvm.rtdata.heap.ref;

import com.litejvm.classfile.constant.ConstantMemberRefInfo;

public class MemberRef extends SymRef{
    public String name;
    public String descriptor;

    void copyMemberRefInfo(ConstantMemberRefInfo memberRefInfo) {
        this.className = memberRefInfo.getClassName();
        this.name = memberRefInfo.getName();
        this.descriptor = memberRefInfo.getDescriptor();
    }
}
