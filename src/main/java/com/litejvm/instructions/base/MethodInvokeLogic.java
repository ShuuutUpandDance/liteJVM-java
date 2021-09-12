package com.litejvm.instructions.base;

import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Slot;
import com.litejvm.rtdata.Thread;
import com.litejvm.rtdata.heap.Method;

public class MethodInvokeLogic {
    public static void invokeMethod(Frame invokerFrame, Method method) {
        Thread thread = invokerFrame.thread;
        Frame frame = new Frame(thread, method);
        thread.pushFrame(frame);

        int argSlotCount = method.argSlotCount;
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i  >= 0; i--) {
                Slot slot = invokerFrame.operandStack.popSlot();
                frame.localVars.setSlot(i, slot);
            }
        }

        if (method.isNative()) {
            if ("registerNatives".equals(method.name)) {
                thread.popFrame();
            } else {
                throw new RuntimeException(String.format("native method: %s.%s%s\n",
                        method.clazz.name, method.name, method.descriptor));
            }
        }
    }
}
