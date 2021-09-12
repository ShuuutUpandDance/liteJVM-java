package com.litejvm.rtdata.heap;

import com.litejvm.classfile.MemberInfo;
import com.litejvm.classfile.attribute.CodeAttribute;

public class Method extends ClassMember{
    public int maxStack;
    public int maxLocals;
    public byte[] code;
    public int argSlotCount;

    void copyAttributes(MemberInfo memberInfo) {
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        if (codeAttribute != null) {
            this.maxStack = codeAttribute.getMaxStack();
            this.maxLocals = codeAttribute.getMaxLocals();
            this.code = codeAttribute.getCode();
        }
    }

    /*
    argSlotCount 不一定等于方法参数列表中的参数个数，原因如下：
    1. long 和 double 类型的参数占 2 个位置
    2. 对于实例方法，编译器会在参数列表前加一个参数，为 this 引用
     */
    public void calcArgSlotCount() {
        MethodDescriptor parsedDescriptor = new MethodDescriptorParser().parse(descriptor);
        for (String parameterType : parsedDescriptor.parameterTypes) {
            argSlotCount++;
            if ("J".equals(parameterType) || "D".equals(parameterType)) {
                argSlotCount++;
            }
        }
        if (!isStatic()) {
            argSlotCount++;
        }
    }
}
