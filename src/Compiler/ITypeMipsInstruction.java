package Compiler;

import Compiler.Register.Register;

public class ITypeMipsInstruction extends MipsInstruction {

    @Override
    public Register getTargetAddress() {
        return null;
    }

    @Override
    public Register getSourceAddress() {
        return null;
    }

    @Override
    public int getOpCode() {
        return 999;
    }

    @Override
    public InstructionType getType() {
        return null;
    }

    @Override
    public int getFunctionCode() {
        return 0;
    }

    public  int getOffset(){
        return 0;
    }
}
