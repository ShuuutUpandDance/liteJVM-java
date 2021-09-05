package com.litejvm.rtdata.heap;

import com.litejvm.classfile.MemberInfo;
import com.litejvm.classfile.attribute.ConstantValueAttribute;

public class Field extends ClassMember{
    public int slotId;
    public int constValueIndex;

    static Field[] newFields(Class clazz, MemberInfo[] memberInfos) {
        Field[] result = new Field[memberInfos.length];
        for (int i = 0; i < memberInfos.length; i++) {
            MemberInfo memberInfo = memberInfos[i];
            result[i] = new Field();
            result[i].clazz = clazz;
            result[i].copyMemberInfo(memberInfo);
            result[i].copyAttributes(memberInfo);
        }
        return result;
    }

    void copyAttributes(MemberInfo memberInfo) {
        ConstantValueAttribute constantValueAttribute = memberInfo.getConstantValueAttribute();
        if (constantValueAttribute != null) {
            constValueIndex = constantValueAttribute.getConstantValueIndex();
        }
    }

    public boolean isVolatile() {
        return (accessFlags & Class.ACC_VOLATILE) != 0;
    }

    public boolean isTransient() {
        return (accessFlags & Class.ACC_TRANSIENT) != 0;
    }

    public boolean isEnum() {
        return (accessFlags & Class.ACC_ENUM) != 0;
    }

    public boolean isLongOrDouble() {
        return "J".equals(descriptor) || "D".equals(descriptor);
    }
}
