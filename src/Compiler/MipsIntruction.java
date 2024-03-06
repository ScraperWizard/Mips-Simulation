package Compiler;

import java.nio.ByteBuffer;

public abstract class MipsIntruction {
    private byte[] opCode;

    public byte[] getOpCode() {
        return opCode;
    }

    public int getOpcode() {
        ByteBuffer buffer = ByteBuffer.wrap(opCode);
        return buffer.getInt();
    }
}

enum InstructionType {
    Itype,
    Rtype,
    Jtype
}
