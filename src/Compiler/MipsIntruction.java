package Compiler;

import Compiler.Address.Address;

public abstract class MipsIntruction {
    private int opCode;
    private Address targetAddress;
    private Address sourceAddress;
    private Address destinationAddress; // Added destination address
    private int functionCode;
    private InstructionType type;

    MipsIntruction(int opCode, Address rs, Address rt, Address rd, int shamt, int functionCode) {
        this.opCode = opCode;
        this.sourceAddress = rs;
        this.targetAddress = rt;
        this.destinationAddress = rd;
        this.type = determineInstructionType(opCode);
        this.functionCode = functionCode;
    }

    MipsIntruction(int opCode, Address rs, Address rt, Address imm) {
        this.opCode = opCode;
        this.sourceAddress = rs;
        this.targetAddress = rt;
        this.destinationAddress = null; // Assuming immediate instructions do not have a destination register
        this.type = determineInstructionType(opCode);
        // You might need to handle immediate values appropriately
    }

    public Address getTargetAddress() {
        return this.targetAddress;
    }

    public Address getSourceAddress() {
        return this.sourceAddress;
    }

    public Address getDestinationAddress() {
        return this.destinationAddress;
    }

    public int getOpCode() {
        return opCode;
    }

    public InstructionType getType() {
        return type;
    }

    public int getFunctionCode() {
        return functionCode;
    }

    // You might need to implement or modify this method based on your requirements
    private InstructionType determineInstructionType(int opCode) {
        // Implement logic to determine instruction type based on opCode
        // Return the appropriate InstructionType
        return InstructionType.Itype; // Change this as needed
    }
}



enum InstructionType {
    Itype,
    Rtype,
    Jtype
}
