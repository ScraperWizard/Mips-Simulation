package Components;

public class ControlUnit {
    int OPCODE;
    int RegDst;
    int Branch;
    int MemRead;
    int MemToReg;
    int ALUOp;
    int MemWrite;
    int ALUSrc;
    int RegWrite;

    ControlUnit(int OPCODE) {
        this.OPCODE = OPCODE;
        controlUnitDecider();
    }

    public void controlUnitDecider() {
        if (OPCODE == 000000 || OPCODE == 0) {
            RegDst = 1;
            ALUSrc = 0;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 0;
            // ALUOp = idk;
        } else if (OPCODE == 000000 || OPCODE == 0) {
        }
    }
    Multiplexer PCSrcMUX = new Multiplexer("PCSrc", Branch);
    Multiplexer MemToRegMUX = new Multiplexer("MemToReg", MemToReg);
}
