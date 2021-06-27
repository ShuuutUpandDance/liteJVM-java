package com.litejvm.instructions.extended;

import com.litejvm.instructions.base.BytecodeReader;
import com.litejvm.instructions.base.Instruction;
import com.litejvm.instructions.loads.aload.ALOAD;
import com.litejvm.instructions.loads.dload.DLOAD;
import com.litejvm.instructions.loads.fload.FLOAD;
import com.litejvm.instructions.loads.iload.ILOAD;
import com.litejvm.instructions.loads.lload.LLOAD;
import com.litejvm.instructions.math.IINC;
import com.litejvm.instructions.stores.astore.ASTORE;
import com.litejvm.instructions.stores.dstore.DSTORE;
import com.litejvm.instructions.stores.fstore.FSTORE;
import com.litejvm.instructions.stores.istore.ISTORE;
import com.litejvm.instructions.stores.lstore.LSTORE;
import com.litejvm.rtdata.Frame;

public class WIDE implements Instruction {
    Instruction modifiedInstruction;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        int code = reader.readUint8();
        switch (code) {
            case 0x15: {
                ILOAD iload = new ILOAD();
                iload.index = reader.readUint16();
                this.modifiedInstruction = iload;
                break;
            }
            case 0x16: {
                LLOAD lload = new LLOAD();
                lload.index = reader.readUint16();
                this.modifiedInstruction = lload;
                break;
            }
            case 0x17: {
                FLOAD fload = new FLOAD();
                fload.index = reader.readUint16();
                this.modifiedInstruction = fload;
                break;
            }
            case 0x18: {
                DLOAD dload = new DLOAD();
                dload.index = reader.readUint16();
                this.modifiedInstruction = dload;
                break;
            }
            case 0x19: {
                ALOAD aload = new ALOAD();
                aload.index = reader.readUint16();
                this.modifiedInstruction = aload;
                break;
            }
            case 0x36: {
                ISTORE istore = new ISTORE();
                istore.index = reader.readUint16();
                this.modifiedInstruction = istore;
                break;
            }
            case 0x37: {
                LSTORE lstore = new LSTORE();
                lstore.index = reader.readUint16();
                this.modifiedInstruction = lstore;
                break;
            }
            case 0x38: {
                FSTORE fstore = new FSTORE();
                fstore.index = reader.readUint16();
                this.modifiedInstruction = fstore;
                break;
            }
            case 0x39: {
                DSTORE dstore = new DSTORE();
                dstore.index = reader.readUint16();
                this.modifiedInstruction = dstore;
                break;
            }
            case 0x3a: {
                ASTORE astore = new ASTORE();
                astore.index = reader.readUint16();
                this.modifiedInstruction = astore;
                break;
            }
            case 0x84: {
                IINC iinc = new IINC();
                iinc.index = reader.readUint16();
                iinc.constant = reader.readInt16();
                this.modifiedInstruction = iinc;
                break;
            }
            case 0xa9: throw new RuntimeException("Unsupported opcode: 0xa9!");
        }
    }

    @Override
    public void execute(Frame frame) {
        this.modifiedInstruction.execute(frame);
    }
}
