package com.litejvm;

import com.litejvm.classfile.MemberInfo;
import com.litejvm.classfile.attribute.CodeAttribute;
import com.litejvm.instructions.InstructionFactory;
import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.instructions.base.Instruction;
import com.litejvm.rtdata.Frame;
import com.litejvm.rtdata.Thread;

public class Interpreter {
    public void interpret(MemberInfo memberInfo) {
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        int maxLocals = codeAttribute.getMaxLocals();
        int maxStack = codeAttribute.getMaxStack();
        byte[] code = codeAttribute.getCode();

        Thread thread = new Thread();
        Frame frame = new Frame(maxLocals, maxStack, thread);
        thread.pushFrame(frame);
        try {
            loop(thread, code);
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
            int nextPC = frame.getNextPC();
            thread.setPc(nextPC);

            //decode
            reader.reset(code, nextPC);
            int opcode = reader.readUint8();
            Instruction instruction = InstructionFactory.newInstruction(opcode);
            instruction.fetchOperands(reader);
            frame.setNextPC(reader.getPC());

            //execute
            System.out.printf("pc:%2d inst:%s, %s\n", nextPC, instruction.getClass().getName(), instruction);
            instruction.execute(frame);
        }
    }
}
