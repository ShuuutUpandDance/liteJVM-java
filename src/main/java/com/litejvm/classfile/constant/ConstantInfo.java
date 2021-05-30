package com.litejvm.classfile.constant;

import com.litejvm.classfile.ClassReader;

public interface ConstantInfo {
    void readInfo(ClassReader reader);
}
