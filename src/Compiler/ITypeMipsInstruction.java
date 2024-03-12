package Compiler;

import Compiler.Register.Register;

public class ITypeMipsInstruction extends MipsInstruction {
    private int opCode;
    private Register sourceAddress;
    private Register targetAddress;
    private String offsetRubishValue;
    private Command command;

    public ITypeMipsInstruction(int opCode, Register sourceAddress, Register targetAddress, int functionCode, Command command) {
        this.opCode = opCode;
        this.sourceAddress = sourceAddress;
        this.targetAddress = targetAddress;
        this.command = command;
        this.offsetRubishValue = "00000000000" + functionCode;
    }

    @Override
    public Command getCommand() {
        return command;
    }

    @Override
    public Register getTargetAddress() {
        return targetAddress;
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
        return InstructionType.Itype;
    }

    @Override
    public int getFunctionCode() {
        return Integer.parseInt(offsetRubishValue);
    }

    public String getOffset(){
        return offsetRubishValue;
    }

    public int getOffsetInt(){
        return Integer.parseInt(offsetRubishValue);
    }
}
