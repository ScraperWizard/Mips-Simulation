package Compiler;

public class InstructionAdapter {
    public static MipsIntruction adaptIType(String opcode, String operation, String immediate) {
        return new ItypeInstruction();
    }

    public static MipsIntruction adaptRType(String opcode, String operation, String register) {
        return new RtypeInstruction();
    }
}