package Components;


public class Multiplexer {

    private String humanNickname;// PCSrcMUX, MemToRegMUX, ALUSrcMUX, RegDstMUX --> Agreed MUXS names
    int controlUnit;
    int AddressDestination; // Output

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

    public Multiplexer(String humanNickname,ControlUnit controlUnit,RegisterMemory registerMemory,SignExtend signExtend){
        this.humanNickname="ALUSrcMUX";
        if(controlUnit.ALUSrc==1)
            AddressDestination=signExtend.output;
        else if(controlUnit.ALUSrc==0)
            AddressDestination=registerMemory.readData2;
        else AddressDestination=-1;
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
            System.out.println("My address is the incremented address because Control Unit == 0");
            AddressDestination = givenAdder.incrementedAddress();
        } else if (controlUnit == 1) {
            System.out.println("My address is the UpperAdd ALURes address because Control Unit == 1");
            AddressDestination = givenUpperAdd.ALUResult;
        }
    }

    private void MemToRegMUX(LowerAdder lowerAdder, DataMemory dataMemory) {
        if(controlUnit == 0){
            System.out.println("My address is the lowerAdder.ALUResult because Control Unit == 0");
            AddressDestination = lowerAdder.output;
        }
        else if (controlUnit == 1){
            System.out.println("My address is the dataMemory.readData ALURes address because Control Unit == 1");
            AddressDestination = dataMemory.valueAtReadData;
        }
    }
}
