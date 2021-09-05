package com.litejvm.rtdata.heap;

import com.litejvm.classfile.MemberInfo;
import com.litejvm.classfile.attribute.CodeAttribute;

public class Method extends ClassMember{
    public int maxStack;
    public int maxLocals;
    public byte[] code;

    void copyAttributes(MemberInfo memberInfo) {
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        if (codeAttribute != null) {
            this.maxStack = codeAttribute.getMaxStack();
            this.maxLocals = codeAttribute.getMaxLocals();
            this.code = codeAttribute.getCode();
        }
    }
}
