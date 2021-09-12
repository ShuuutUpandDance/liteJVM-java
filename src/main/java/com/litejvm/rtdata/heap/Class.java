package com.litejvm.rtdata.heap;

import com.litejvm.classfile.ClassFile;
import com.litejvm.classfile.MemberInfo;
import com.litejvm.classfile.constant.*;
import com.litejvm.rtdata.heap.ref.ClassRef;
import com.litejvm.rtdata.heap.ref.FieldRef;
import com.litejvm.rtdata.heap.ref.InterfaceMethodRef;
import com.litejvm.rtdata.heap.ref.MethodRef;

import java.util.ArrayList;
import java.util.List;

public class Class {
    static int ACC_PUBLIC         = 0x0001; // class field method
    static int ACC_PRIVATE        = 0x0002; //       field method
    static int ACC_PROTECTED      = 0x0004; //       field method
    static int ACC_STATIC         = 0x0008; //       field method
    static int ACC_FINAL          = 0x0010; // class field method
    static int ACC_SUPER          = 0x0020; // class
    static int ACC_SYNCHRONIZED   = 0x0020; //             method
    static int ACC_VOLATILE       = 0x0040; //       field
    static int ACC_BRIDGE         = 0x0040; //             method
    static int ACC_TRANSIENT      = 0x0080; //       field
    static int ACC_VARARGS        = 0x0080; //             method
    static int ACC_NATIVE         = 0x0100; //             method
    static int ACC_INTERFACE      = 0x0200; // class
    static int ACC_ABSTRACT       = 0x0400; // class       method
    static int ACC_STRICT         = 0x0800; //             method
    static int ACC_SYNTHETIC      = 0x1000; // class field method
    static int ACC_ANNOTATION     = 0x2000; // class
    static int ACC_ENUM           = 0x4000; // class field

    public int accessFlags;
    public String name;
    public String superClassName;
    public List<String> interfaceNames;
    public ConstantPool constantPool;
    public List<Field> fields;
    public List<Method> methods;
    public ClassLoader classLoader;
    public Class superClass;
    public List<Class> interfaces;
    public int instanceSlotCount;
    public int staticSlotCount;
    public Slots staticVars;
    public boolean initStarted;

    public Class(ClassFile classFile) {
        this.accessFlags = classFile.getAccessFlags();
        this.name = classFile.getThisClassName();
        this.superClassName = classFile.getSuperClassName();
        this.interfaceNames = classFile.getInterfacesNames();
        this.constantPool = newConstantPool(classFile.getConstantPool());
        this.fields = newFields(classFile.getFields());
        this.methods = newMethods(classFile.getMethods());
    }

    public Method getMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    public Method getClinitMethod() {
        return getStaticMethod("<clinit>", "()V");
    }

    public Method getStaticMethod(String name, String descriptor) {
        for (Method method : methods) {
            if (method.isStatic() && name.equals(method.name) && descriptor.equals(method.descriptor)) {
                return method;
            }
        }
        return null;
    }

    public boolean isPublic() {
        return (this.accessFlags & ACC_PUBLIC) != 0;
    }

    public boolean isFinal() {
        return (this.accessFlags & ACC_FINAL) != 0;
    }

    public boolean isSuper() {
        return (this.accessFlags & ACC_SUPER) != 0;
    }

    public boolean isInterface() {
        return (this.accessFlags & ACC_INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (this.accessFlags & ACC_ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (this.accessFlags & ACC_SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (this.accessFlags & ACC_ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (this.accessFlags & ACC_ENUM) != 0;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public Slots getStaticVars() {
        return staticVars;
    }

    public boolean isInitStarted() {
        return initStarted;
    }

    public void startInit() {
        this.initStarted = true;
    }

    public void setInitStarted(boolean initStarted) {
        this.initStarted = initStarted;
    }

    ConstantPool newConstantPool(com.litejvm.classfile.constant.ConstantPool constantPool) {
        List<ConstantInfo> constantInfoList = constantPool.getConstantInfo();

        Object[] consts = new Object[constantInfoList.size()];

        ConstantPool cp = new ConstantPool();
        cp.clazz = this;

        for (int i = 0; i < constantInfoList.size(); i++) {
            ConstantInfo constantInfo = constantInfoList.get(i);
            if (constantInfo instanceof ConstantIntegerInfo) {
                consts[i] = ((ConstantIntegerInfo) constantInfo).getValue();
            } else if (constantInfo instanceof ConstantFloatInfo) {
                consts[i] = ((ConstantFloatInfo) constantInfo).getValue();
            } else if (constantInfo instanceof ConstantLongInfo) {
                consts[i++] = ((ConstantLongInfo) constantInfo).getValue();
            } else if (constantInfo instanceof ConstantDoubleInfo) {
                consts[i++] = ((ConstantDoubleInfo) constantInfo).getValue();
            } else if (constantInfo instanceof ConstantStringInfo) {
                consts[i] = ((ConstantStringInfo) constantInfo).getContent();
            } else if (constantInfo instanceof ConstantClassInfo) {
                consts[i] = new ClassRef(cp, (ConstantClassInfo) constantInfo);
            } else if (constantInfo instanceof ConstantFieldRefInfo) {
                consts[i] = new FieldRef(cp, (ConstantFieldRefInfo) constantInfo);
            } else if (constantInfo instanceof ConstantMethodRefInfo) {
                consts[i] = new MethodRef(cp, (ConstantMethodRefInfo) constantInfo);
            } else if (constantInfo instanceof ConstantInterfaceMethodRefInfo) {
                consts[i] = new InterfaceMethodRef(cp, (ConstantInterfaceMethodRefInfo) constantInfo);
            } else {
                // TODO
            }
        }

        cp.consts = consts;
        return cp;
    }

    List<Field> newFields(List<MemberInfo> memberInfoList) {
        List<Field> result = new ArrayList<>();
        for (MemberInfo memberInfo : memberInfoList) {
            Field field = new Field();
            field.clazz = this;
            field.copyMemberInfo(memberInfo);
            result.add(field);
        }
        return result;
    }

    List<Method> newMethods(List<MemberInfo> memberInfoList) {
        List<Method> result = new ArrayList<>();
        for (MemberInfo memberInfo : memberInfoList) {
            Method method = new Method();
            method.clazz = this;
            method.copyMemberInfo(memberInfo);
            method.copyAttributes(memberInfo);
            method.calcArgSlotCount();
            result.add(method);
        }
        return result;
    }

    public boolean isAccessibleTo(Class other) {
        return isPublic() || getPackageName().equals(other.getPackageName());
    }

    public boolean isAssignableFrom(Class other) {
        if (this == other) {
            return true;
        }

        if (!this.isInterface()) {
            return other.isSubClassOf(this);
        } else {
            return other.isImplements(this);
        }
    }

    public boolean isSubClassOf(Class other) {
        Class supClazz = this.superClass;
        while (supClazz != null) {
            if (supClazz == other) {
                return true;
            }
            supClazz = supClazz.superClass;
        }
        return false;
    }

    public boolean isSuperClassOf(Class other) {
        return other.isSubClassOf(this);
    }

    public boolean isImplements(Class other) {
        Class current = this;
        while (current != null) {
            for (Class anInterface : interfaces) {
                if (anInterface == other || anInterface.isSubInterfaceOf(other)) {
                    return true;
                }
            }
            current = current.superClass;
        }
        return false;
    }

    public boolean isSubInterfaceOf(Class other) {
        for (Class anInterface : interfaces) {
            if (anInterface == other || anInterface.isSubInterfaceOf(other)) {
                return true;
            }
        }
        return false;
    }

    public String getPackageName() {
        int index = name.lastIndexOf("/");
        if (index > 0) {
            return name.substring(0, index);
        }
        return "";
    }
}
