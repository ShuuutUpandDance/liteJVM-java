package com.litejvm.classfile.attribute;

import com.litejvm.classfile.ClassReader;
import com.litejvm.classfile.constant.ConstantPool;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAttributeInfo implements AttributeInfo{

    public static List<AttributeInfo> readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attrCount = reader.readUint16();

        List<AttributeInfo> result = new ArrayList<>();
        for (int i = 0; i < attrCount; i++) {
            result.add(readAttribute(reader, constantPool));
        }

        return result;
    }

    public static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrNameIndex = reader.readUint16();
        String attrName = constantPool.getUTF8(attrNameIndex);

        int attrLen = reader.readUint32();

        AttributeInfo attributeInfo = newAttributeInfo(attrName, attrLen, constantPool);
        attributeInfo.readInfo(reader);
        return attributeInfo;
    }

    static AttributeInfo newAttributeInfo(String attrName, int attrLen, ConstantPool constantPool) {
        switch (attrName) {
            case "Code": return new CodeAttribute(constantPool);
            case "ConstantValue": return new ConstantValueAttribute();
            case "Deprecated": return new DeprecatedAttribute();
            case "Exceptions": return new ExceptionsAttribute();
            case "LineNumberTable": return new LineNumberTableAttribute();
            case "LocalVariableTable": return new LocalVariableTable();
            case "SourceFile": return new SourceFileAttribute(constantPool);
            case "Synthetic": return new SyntheticAttribute();
            default: return new UnparsedAttribute(attrName, attrLen);
        }
    }
}
