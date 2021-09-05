package com.litejvm;

import com.litejvm.instructions.InstructionFactory;
import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.instructions.base.Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Thread;
import com.litejvm.rtdata.heap.Method;

public class Interpreter {
    public void interpret(Method method) {
        Thread thread = new Thread();
        Frame frame = new Frame(thread, method);
        thread.pushFrame(frame);
        try {
            loop(thread, method.code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("LocalVars: %s\n", frame.getLocalVars());
            System.out.printf("OperandStack: %s\n", frame.getOperandStack());
        }
    }

    void loop(Thread thread, byte[] code) {
        Frame frame = thread.popFrame();
        BytecodeReader reader = new BytecodeReader();

        while (true) {
            // 1. get instruction bytecode by PC
            int nextPC = frame.getNextPC();
            thread.setPc(nextPC);
            reader.reset(code, nextPC);
            int opcode = reader.readUint8();

            // 2. decode instruction
            Instruction instruction = InstructionFactory.newInstruction(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPC());

            // 3. execute instruction
            System.out.printf("pc:%2d inst:%s, %s\n", nextPC, instruction.getClass().getName(), instruction);
            instruction.execute(frame);
        }
    }
}
