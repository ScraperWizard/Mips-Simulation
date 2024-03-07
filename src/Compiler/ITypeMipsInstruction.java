package Compiler;

import Compiler.Address.Address;

public class ITypeMipsInstruction extends MipsInstruction {

    @Override
    public Address getTargetAddress() {
        return null;
    }

    @Override
    public Address getSourceAddress() {
        return null;
    }

    @Override
    public int getOpCode() {
        return 0;
    }

    @Override
    public InstructionType getType() {
        return null;
    }

    @Override
    public int getFunctionCode() {
        return 0;
    }
}
