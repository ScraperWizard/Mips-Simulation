package Compiler;

import Compiler.Register.Register;

public class JTypeMipsInstruction extends MipsInstruction {
    private Register target;
    private int opcode;
    private int functionCode;
    private Command command;

    public JTypeMipsInstruction(int opcode, Register target, int functionCode, Command command) {
        this.opcode = opcode;
        this.target = target;
        this.functionCode = functionCode;
        this.command = command;
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
