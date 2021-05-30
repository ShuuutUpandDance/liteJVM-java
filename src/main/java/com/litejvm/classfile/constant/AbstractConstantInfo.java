package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassReader;

public abstract class AbstractConstantInfo implements ConstantInfo{
    protected static final int CLASS = 7;
    protected static final int FIELD_REF = 9;
    protected static final int METHOD_REF = 10;
    protected static final int INTERFACE_METHOD_REF = 11;
    protected static final int STRING = 8;
    protected static final int INTEGER = 3;
    protected static final int FLOAT = 4;
    protected static final int LONG = 5;
    protected static final int DOUBLE = 6;
    protected static final int NAME_AND_TYPE = 12;
    protected static final int UTF8 = 1;
    protected static final int METHOD_HANDLE = 15;
    protected static final int METHOD_TYPE = 16;
    protected static final int INVOKE_DYNAMIC = 18;

    public static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool) {
        int tag = reader.readUint8();
        ConstantInfo constantInfo = newConstantInfo(tag, constantPool);
        constantInfo.readInfo(reader);
        return constantInfo;
    }

    private static ConstantInfo newConstantInfo(int tag, ConstantPool constantPool) {
        switch (tag) {
            case INTEGER: return new ConstantIntegerInfo();
            case FLOAT: return new ConstantFloatInfo();
            case LONG: return new ConstantLongInfo();
            case DOUBLE: return new ConstantDoubleInfo();
            case UTF8: return new ConstantUTF8Info();
            case STRING: return new ConstantStringInfo(constantPool);
            case CLASS: return new ConstantClassInfo(constantPool);
            case FIELD_REF: return new ConstantFieldRefInfo(constantPool);
            case METHOD_REF: return new ConstantMethodRefInfo(constantPool);
            case INTERFACE_METHOD_REF: return new ConstantInterfaceMethodRefInfo(constantPool);
            case NAME_AND_TYPE: return new ConstantNameAndTypeInfo(constantPool);

            default: throw new RuntimeException("Unsupported tag!");
        }
    }
}
