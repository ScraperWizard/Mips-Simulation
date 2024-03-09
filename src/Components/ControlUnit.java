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

    public ControlUnit() {

    }

    public void update (int OPCODE) {
        this.OPCODE = OPCODE;
        controlUnitDecider();
    }

    public void controlUnitDecider() {
        //  R-Type --> Sure about the Signals
        if (OPCODE == 000000 || OPCODE == 0) {
            System.out.println("ControlUnit: I chose R-type control unit");
            RegDst = 1;
            ALUSrc = 0;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 0;
            ALUOp = 2;
        // J Label --> PLEASE DOUBLE CHECK the control units
        } else if (OPCODE == 000010 || OPCODE == 2) {
            System.out.println("ControlUnit: I chose j label control unit");
            RegDst = 0;
            ALUSrc = 0;
            MemToReg = 0;
            RegWrite = 0;
            MemRead = 0;
            MemWrite = 0;
            Branch = 1;
            ALUOp = 0;
        }
        // Jal Label --> PLEASE DOUBLE CHECK the control units
        else if (OPCODE == 000011 || OPCODE == 3){
            System.out.println("ControlUnit: I chose jal label control unit");
            RegDst = 1;
            ALUSrc = 0;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 1;
            ALUOp = 0;
        }
        // beq --> Sure about the Signals
        else if(OPCODE == 000100 || OPCODE == 4){
            System.out.println("ControlUnit: I chose beq control unit");
            RegDst = 0;
            ALUSrc = 0;
            MemToReg = 0;
            RegWrite = 0;
            MemRead = 0;
            MemWrite = 0;
            Branch = 1;
            ALUOp = 1;
        }
        // addi --> PLEASE DOUBLE CHECK the control units
        else if(OPCODE == 001000 || OPCODE == 8){
            System.out.println("ControlUnit: I chose addi control unit");
            RegDst = 0;
            ALUSrc = 1;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 0;
            ALUOp = 0;
        }
        // andi --> PLEASE DOUBLE CHECK the control units
        else if(OPCODE == 001100 || OPCODE == 12){
            System.out.println("ControlUnit: I chose andi control unit");
            RegDst = 0;
            ALUSrc = 1;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 0;
            ALUOp = 3;
        }
        // ori --> PLEASE DOUBLE CHECK the control units
        else if(OPCODE == 001101 || OPCODE == 13){
            System.out.println("ControlUnit: I chose ori control unit");
            RegDst = 0;
            ALUSrc = 1;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 0;
            ALUOp = 3;
        }
        // lw --> Sure about the Signals
        else if(OPCODE == 100011 || OPCODE == 35){
            System.out.println("ControlUnit: I chose lw control unit");
            RegDst = 0;
            ALUSrc = 1;
            MemToReg = 1;
            RegWrite = 1;
            MemRead = 1;
            MemWrite = 0;
            Branch = 0;
            ALUOp = 0;
        }
        // sw --> Sure about the Signals
        else if(OPCODE == 101011 || OPCODE == 43){
            System.out.println("ControlUnit: I chose sw control unit");
            RegDst = 0;
            ALUSrc = 1;
            MemToReg = 0;
            RegWrite = 0;
            MemRead = 0;
            MemWrite = 1;
            Branch = 0;
            ALUOp = 0;
        }

    }

}
