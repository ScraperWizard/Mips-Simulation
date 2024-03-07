package Compiler;

import Compiler.Address.Address;

public class RTypeMipsInstruction extends MipsInstruction {
    private int opCode;
    private Address sourceAddress;
    private Address targetAddress;
    private Address destinationAddress; // rd in R-type instructions
    private int shamt;
    private int functionCode;

    public RTypeMipsInstruction(int opCode, Address rs, Address rt, Address rd, int shamt, int functionCode) {
        this.opCode = opCode;
        this.sourceAddress = rs;
        this.targetAddress = rt;
        this.destinationAddress = rd;
        this.shamt = shamt;
        this.functionCode = functionCode;
    }

    @Override
    public Address getTargetAddress() {
        return targetAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    @Override
    public Address getSourceAddress() {
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
