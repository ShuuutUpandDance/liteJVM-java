package com.litejvm.classfile;

import com.litejvm.classfile.attribute.AbstractAttributeInfo;
import com.litejvm.classfile.attribute.AttributeInfo;
import com.litejvm.classfile.constant.ConstantClassInfo;
import com.litejvm.classfile.constant.ConstantPool;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassFile {
    private Hex32 magic;
    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private List<Integer> interfaces;
    private List<MemberInfo> fields;
    private List<MemberInfo> methods;
    private List<AttributeInfo> attributes;

    private ClassReader reader;

    private ClassFile() {}

    private static final List<Integer> MAJOR_V_WITH_MINOR_V_0 = Arrays.asList(46, 47, 48, 49, 50, 51, 52);

    public static ClassFile parse(byte[] input) {
        ClassFile classFile = new ClassFile();
        init(classFile, input);
        return classFile;
    }

    private static void init(ClassFile classFile, byte[] input) {
        classFile.reader = new ClassReader(input);
        readAndCheckMagic(classFile);
        readAndCheckVersion(classFile);
        classFile.constantPool = ConstantPool.readConstantPool(classFile.reader);
        classFile.accessFlags = classFile.reader.readUint16();
        classFile.thisClass = classFile.reader.readUint16();
        classFile.superClass = classFile.reader.readUint16();
        classFile.interfaces = classFile.reader.readUint16Table();
        classFile.fields = MemberInfo.readMembers(classFile.reader, classFile.constantPool);
        classFile.methods = MemberInfo.readMembers(classFile.reader, classFile.constantPool);
        classFile.attributes = AbstractAttributeInfo.readAttributes(classFile.reader, classFile.constantPool);
    }

    private static void readAndCheckVersion(ClassFile classFile) {
        classFile.minorVersion = classFile.reader.readUint16();
        classFile.majorVersion = classFile.reader.readUint16();

        if (classFile.majorVersion == 45) {
            return;
        } else if (MAJOR_V_WITH_MINOR_V_0.contains(classFile.majorVersion) && classFile.minorVersion == 0) {
            return;
        }
        throw new UnsupportedClassVersionError();
    }


    private static void readAndCheckMagic(ClassFile classFile) {
        Hex32 hex32 = classFile.reader.readHex32();
        if (!"CAFEBABE".equalsIgnoreCase(new String(hex32.getContent()))) {
            throw new ClassFormatError("wrong magic");
        }
    }

    public Hex32 getMagic() {
        return magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public String getThisClassName() {
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.constantPool.getConstantInfo().get(thisClass);
        return constantClassInfo.getName();
    }

    public int getSuperClass() {
        return superClass;
    }

    public String getSuperClassName() {
        if (superClass > 0) {
            ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.constantPool.getConstantInfo().get(superClass);
            return constantClassInfo.getName();
        }
        return "";
    }

    public List<Integer> getInterfaces() {
        return interfaces;
    }

    public List<String> getInterfacesNames() {
        return interfaces.stream().map(idx -> {
            ConstantClassInfo constantClassInfo = (ConstantClassInfo) this.constantPool.getConstantInfo().get(idx);
            return constantClassInfo.getName();
        }).collect(Collectors.toList());
    }

    public List<MemberInfo> getFields() {
        return fields;
    }

    public List<MemberInfo> getMethods() {
        return methods;
    }

    public List<AttributeInfo> getAttributes() {
        return attributes;
    }

    public static class Hex64 {
        private final char[] content;

        public Hex64(char[] input) {
            content = Arrays.copyOf(input, 16);
        }

        public char[] getContent() {
            return content;
        }
    }

    public static class Hex32 {
        private final char[] content;

        public Hex32(char[] input) {
            content = Arrays.copyOf(input, 8);
        }

        public char[] getContent() {
            return content;
        }
    }

    public static class Hex16 {
        private final char[] content;

        public Hex16(char[] input) {
            content = Arrays.copyOf(input, 4);
        }

        public char[] getContent() {
            return content;
        }
    }

    public static class Hex8 {
        private final char[] content;

        public Hex8(char[] input) {
            content = Arrays.copyOf(input, 2);
        }

        public char[] getContent() {
            return content;
        }
    }
}
