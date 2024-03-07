package Compiler;

import Compiler.Address.Address;

public abstract class MipsInstruction {
    public abstract  Address getTargetAddress();
    public abstract Address getSourceAddress();
    public abstract int getOpCode();
    public abstract InstructionType getType();
    public abstract int getFunctionCode();
}
