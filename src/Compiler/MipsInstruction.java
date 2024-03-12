package Compiler;

import Compiler.Register.Register;

public abstract class MipsInstruction {
    public abstract Command getCommand();
    public abstract  Register getTargetAddress();
    public abstract Register getSourceAddress();
    public abstract int getOpCode();
    public abstract InstructionType getType();
    public abstract int getFunctionCode();
}
