package Compiler;

import Compiler.Register.Register;

public class JTypeMipsInstruction extends MipsInstruction {
    private Register target;
    private int opcode;
    private int functionCode;
    private Command command;
    private int addressToJump;

    public JTypeMipsInstruction(int opcode, int functionCode, Command command, int addressToJump) {
        this.opcode = opcode;
        this.functionCode = functionCode;
        this.command = command;
        this.addressToJump = addressToJump;
    }

    public int getAddressToJump() {
        return addressToJump;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public Register getTargetAddress() {
        return target;
    }

    @Override
    public Register getSourceAddress() {
        return target;
    }

    @Override
    public int getOpCode() {
        return opcode;
    }

    @Override
    public InstructionType getType() {
        return InstructionType.Jtype;
    }

    @Override
    public int getFunctionCode() {
        return functionCode;
    }
}
