package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassReader;

public class ConstantMemberRefInfo extends AbstractConstantInfo{
    private final ConstantPool constantPool;

    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantMemberRefInfo(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    public String getClassName() {
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.constantPool.getConstantInfo().get(classIndex);
        return constantClassInfo.getName();
    }

    public String getName() {
        ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) this.constantPool.getConstantInfo().get(nameAndTypeIndex);
        return constantNameAndTypeInfo.getName();
    }

    public String getDescriptor() {
        ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) this.constantPool.getConstantInfo().get(nameAndTypeIndex);
        return constantNameAndTypeInfo.getDescriptor();
    }
}
