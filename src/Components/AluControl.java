package Components;
import Compiler.MipsInstruction;

public class AluControl {
    //input
    ControlUnit controlUnit;
    int functionCode;
    int opCode;
    MipsInstruction mipsInstruction;

    //output;
    int operation;



    public  AluControl(){

    }

    public void update(ControlUnit controlUnit,MipsInstruction mipsInstruction){
        this.controlUnit=controlUnit;
        this.functionCode=mipsInstruction.getFunctionCode();
        this.opCode= mipsInstruction.getOpCode();
        getOperation(controlUnit.ALUOp);
    }

    public void getOperation(int ALUOp){
        System.out.println("ALU op is: "+ ALUOp);
        //missing addi, andi, ori,sll
        if(ALUOp==0)//sw,lw,addi
            operation=2;
        else if(ALUOp==1)//beq
            operation=6;
        else if(ALUOp==3){
            if(opCode==13)//or
                operation=1;
            else if(opCode==12)//and
                operation=0;
            else operation=-1;
        }
        else if(ALUOp==2){//R Type
            if(functionCode==32)//add
                operation=2;
            else if(functionCode==34)//sub
                operation=6;
            else if(functionCode==36)//and
                operation=0;
            else if(functionCode==37)//or
                operation=1;
            else if(functionCode==39)//nor
                operation=12;
            else if(functionCode==42)//slt
                operation=7;
            else
                operation=-1;
        }
        else operation=-1;

        System.out.println("operation is: "+operation);
        return;
    }

}