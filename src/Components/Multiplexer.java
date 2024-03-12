package Components;
import Compiler.MipsInstruction;

public class Multiplexer {

    private String humanNickname;// PCSrcMUX, MemToRegMUX, ALUSrcMUX, RegDstMUX --> Agreed MUXS names
    int controlUnit;
    public int AddressDestination; // Output

/*    public Multiplexer(int controlUnit){
        this.controlUnit = controlUnit;
    }

    public Multiplexer(String humanNickname) {
        this.humanNickname = humanNickname;
    } */
    //PCSrcMUX
    public Multiplexer(String humanNickname, AndGate andGate, UpperAdd givenUpperAdd, Adder givenAdder) {
        this.humanNickname = humanNickname;
        this.controlUnit = andGate.controlUnitForPCSrc;
        PCSrcMUX(givenUpperAdd, givenAdder);

    }
    public Multiplexer (String humanNickname,ControlUnit controlUnit, LowerAdder lowerAdder, DataMemory dataMemory){
        this.humanNickname = humanNickname;
        this.controlUnit = controlUnit.MemToReg;
        MemToRegMUX(lowerAdder, dataMemory);
    }

    public Multiplexer(ControlUnit controlUnit,MipsInstruction instruction){

    }

    public Multiplexer(String humanNickname, ControlUnit controlUnit, RegisterMemory registerMemory, SignExtend signExtend) {
        System.out.println("[Multiplexer - ");

        // Print the values of the arguments
        System.out.println("  humanNickname: " + humanNickname);
        System.out.println("  controlUnit: " + controlUnit.ALUSrc);
        System.out.println("  registerMemory: " + registerMemory.readData2);
        System.out.println("  signExtend: " + signExtend.output);

        this.humanNickname = "ALUSrcMUX";

        // Rest of your constructor logic
        if (controlUnit.ALUSrc == 1)
            AddressDestination = signExtend.output;
        else if (controlUnit.ALUSrc == 0)
            AddressDestination = registerMemory.readData2;
        else
            AddressDestination = -1;
    }


//    private void MUXDecider (UpperAdd givenUpperAdd, Adder givenAdder){
//        if(humanNickname.equalsIgnoreCase("PCSrc")){
//            System.out.println("I chose PCSrc, because I received PCSrc as human nickname");
//            PCSrcMUX(givenUpperAdd, givenAdder);
//        }
//        if(humanNickname.equalsIgnoreCase("MemToReg")){
//
//        }
//    }

    // PSCrcMUX
    private void PCSrcMUX (UpperAdd givenUpperAdd, Adder givenAdder) {
        if (controlUnit == 0) {
            System.out.println("PC will get "+ givenAdder.nextAddress);
            AddressDestination = givenAdder.nextAddress;
        } else if (controlUnit == 1) {
            System.out.println("Jumping to :" +givenUpperAdd.ALUResult );
            AddressDestination = givenUpperAdd.ALUResult;
        }
    }

    private void MemToRegMUX(LowerAdder lowerAdder, DataMemory dataMemory) {
        if(controlUnit == 0){
            System.out.println("Selected ALU Result");
            AddressDestination = lowerAdder.output;
        }
        else if (controlUnit == 1){
            System.out.println("Selected Memory result");
            AddressDestination = dataMemory.valueAtReadData;
        }
    }

}
