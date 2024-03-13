package Components;
import Compiler.MipsInstruction;

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
    private MipsInstruction instruction;

    public ControlUnit() {

    }

    public void update (MipsInstruction instruction) {
        this.OPCODE = instruction.getOpCode();
        this.instruction = instruction;
        controlUnitDecider();
    }

    public MipsInstruction getInstruction() {
        return this.instruction;
    }
    public void controlUnitDecider() {
        //  R-Type --> Sure about the Signals
        if ( OPCODE == 0) {
            System.out.println("ControlUnit: I chose R-type control unit");
            RegDst = 1;
            ALUSrc = 0;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 0;
            ALUOp = 2;
        // J Label --> PLEASE DOUBLE-CHECK the control units
        } else if (OPCODE == 2) {
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
        // Jal Label --> PLEASE DOUBLE-CHECK the control units
        else if ( OPCODE == 3){
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
        else if( OPCODE == 4){
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
        // addi --> PLEASE DOUBLE-CHECK  the control units
        else if(OPCODE == 8){
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
        // andi --> PLEASE DOUBLE-CHECK the control units
        else if( OPCODE == 12){
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
        // ori --> PLEASE DOUBLE-CHECK the control units
        else if( OPCODE == 13){
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
        else if( OPCODE == 35){
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
        else if( OPCODE == 43){
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
        else if( OPCODE ==7){
            System.out.println("ControlUnit: I chose SLL control unit");
            RegDst = 0;
            ALUSrc = 1;
            MemToReg = 0;
            RegWrite = 1;
            MemRead = 0;
            MemWrite = 0;
            Branch = 0;
            ALUOp = 3;

        }

    }

}
