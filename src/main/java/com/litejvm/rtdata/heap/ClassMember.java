package com.litejvm.rtdata.heap;

import com.litejvm.classfile.MemberInfo;

public class ClassMember {
    public int accessFlags;
    public String name;
    public String descriptor;
    public Class clazz;

    void copyMemberInfo(MemberInfo memberInfo) {
        this.accessFlags = memberInfo.getAccessFlags();
        this.name = memberInfo.getName();
        this.descriptor = memberInfo.getDescriptor();
    }

    public boolean isAccessibleTo(Class other) {
        if (isPublic()) {
            return true;
        }

        if (isProtected()) {
            return other == clazz
                    || other.isSubClassOf(clazz)
                    || clazz.getPackageName().equals(other.getPackageName());
        }

        if (!isPrivate()) {
            return clazz.getPackageName().equals(other.getPackageName());
        }

        return clazz == other;
    }

    public boolean isAbstract() {
        return (this.accessFlags & Class.ACC_ABSTRACT) != 0;
    }

    public boolean isPublic() {
        return (this.accessFlags & Class.ACC_PUBLIC) != 0;
    }

    public boolean isNative() {
        return (this.accessFlags & Class.ACC_NATIVE) != 0;
    }

    public boolean isPrivate() {
        return (this.accessFlags & Class.ACC_PRIVATE) != 0;
    }

    public boolean isProtected() {
        return (this.accessFlags & Class.ACC_PROTECTED) != 0;
    }

    public boolean isStatic() {
        return (this.accessFlags & Class.ACC_STATIC) != 0;
    }

    public boolean isFinal() {
        return (this.accessFlags & Class.ACC_FINAL) != 0;
    }

    public boolean isSynthetic() {
        return (this.accessFlags & Class.ACC_SYNTHETIC) != 0;
    }
}
