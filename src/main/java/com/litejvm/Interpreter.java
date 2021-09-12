package com.litejvm;

import com.litejvm.instructions.InstructionFactory;
import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.instructions.base.Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Thread;
import com.litejvm.rtdata.heap.Method;

public class Interpreter {
    public void interpret(Method method, boolean logInst) {
        Thread thread = new Thread();
        Frame frame = new Frame(thread, method);
        thread.pushFrame(frame);
        try {
            loop(thread, logInst);
        } catch (Exception e) {
            logFrames(thread);
            e.printStackTrace();
        }
    }

    void loop(Thread thread, boolean logInst) {
        BytecodeReader reader = new BytecodeReader();
        do {
            // 1. get instruction bytecode by PC
            Frame frame = thread.currentFrame();
            int nextPC = frame.getNextPC();
            thread.setPc(nextPC);
            reader.reset(frame.method.code, nextPC);
            int opcode = reader.readUint8();

            // 2. decode instruction
            Instruction instruction = InstructionFactory.newInstruction(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPC());

            if (logInst) {
                logInstruction(frame, instruction);
            }

            instruction.execute(frame);

        } while (!thread.isStackEmpty());
    }

    private void logFrames(Thread thread) {
        while (!thread.isStackEmpty()) {
            Frame frame = thread.popFrame();
            Method method = frame.method;
            String className = method.clazz.name;
            System.out.printf(">> pc:%d, %s.%s%s \n",
                    frame.nextPC, className, method.name, method.descriptor);
        }
    }

    private void logInstruction(Frame frame, Instruction instruction) {
        Method method = frame.method;
        String className = method.clazz.name;
        String methodName = method.name;
        int pc = frame.thread.pc;
        System.out.printf("%s.%s() #%d %s\n",
                className, methodName, pc, instruction);
    }
}
