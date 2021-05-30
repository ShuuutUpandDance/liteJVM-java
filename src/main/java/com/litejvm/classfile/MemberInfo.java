package com.litejvm.classfile;

import com.litejvm.classfile.attribute.AbstractAttributeInfo;
import com.litejvm.classfile.attribute.AttributeInfo;
import com.litejvm.classfile.constant.ConstantPool;

import java.util.ArrayList;
import java.util.List;

public class MemberInfo {
    private final ConstantPool constantPool;

    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final List<AttributeInfo> attributes;

    public MemberInfo(ConstantPool constantPool, int accessFlags, int nameIndex, int descriptorIndex, List<AttributeInfo> attributes) {
        this.constantPool = constantPool;
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }

    public static List<MemberInfo> readMembers(ClassReader reader, ConstantPool constantPool) {
        int memberCount = reader.readUint16();
        List<MemberInfo> result = new ArrayList<>();
        for (int i = 0; i < memberCount; i++) {
            result.add(readMember(reader, constantPool));
        }
        return result;
    }

    public static MemberInfo readMember(ClassReader reader, ConstantPool constantPool) {
        return new MemberInfo(constantPool,
                reader.readUint16(),
                reader.readUint16(),
                reader.readUint16(),
                AbstractAttributeInfo.readAttributes(reader, constantPool)
                );
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public String getName() {
        return this.constantPool.getUTF8(this.nameIndex);
    }

    public String getDescriptor() {
        return this.constantPool.getUTF8(this.descriptorIndex);
    }
}
