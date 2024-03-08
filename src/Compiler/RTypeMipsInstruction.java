package Compiler;

import Compiler.Register.Register;

public class RTypeMipsInstruction extends MipsInstruction {
    private int opCode;
    private Register sourceAddress;
    private Register targetAddress;
    private Register destinationAddress; // rd in R-type instructions
    private int shamt;
    private int functionCode;

    public RTypeMipsInstruction(int opCode, Register rs, Register rt, Register rd, int shamt, int functionCode) {
        this.opCode = opCode;
        this.sourceAddress = rs;
        this.targetAddress = rt;
        this.destinationAddress = rd;
        this.shamt = shamt;
        this.functionCode = functionCode;
    }

    @Override
    public Register getTargetAddress() {
        return targetAddress;
    }

    public Register getDestinationAddress() {
        return destinationAddress;
    }

    @Override
    public Register getSourceAddress() {
        return sourceAddress;
    }

    @Override
    public int getOpCode() {
        return opCode;
    }

    @Override
    public InstructionType getType() {
        return InstructionType.Rtype;
    }

    @Override
    public int getFunctionCode() {
        return functionCode;
    }

    public int getShamt() {
        return shamt;
    }
}
