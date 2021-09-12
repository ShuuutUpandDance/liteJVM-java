package com.litejvm.instructions.base;

import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Thread;
import com.litejvm.rtdata.heap.Class;
import com.litejvm.rtdata.heap.Method;

public class ClassInitLogic {
    public static void initClass(Thread thread, Class clazz) {
        clazz.startInit();
        scheduleClinit(thread, clazz);
        initSuperClass(thread, clazz);
    }

    private static void scheduleClinit(Thread thread, Class clazz){
        Method clinitMethod = clazz.getClinitMethod();
        if (clinitMethod != null) {
            Frame frame = new Frame(thread, clinitMethod);
            thread.pushFrame(frame);
        }
    }

    private static void initSuperClass(Thread thread, Class clazz) {
        if (!clazz.isInterface()) {
            Class superClass = clazz.superClass;
            if (superClass != null && !superClass.isInitStarted()) {
                initClass(thread, superClass);
            }
        }
    }
}
