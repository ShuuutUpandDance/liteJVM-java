package com.litejvm.rtdata.heap;

import java.util.ArrayList;
import java.util.List;

public class MethodDescriptor {
    public List<String> parameterTypes = new ArrayList<>();
    public String returnType;

    public void addParameterType(String t) {
        parameterTypes.add(t);
    }
}
