package Components;


public class Multiplexer {

    private String humanNickname;// PCSrcMUX, MemToRegMUX, ALUSrcMUX, RegDstMUX --> Agreed MUXS names
    int controlUnit;
    int AddressDestination;

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

    private void MemToRegMUX (LowerAdder lowerAdder, DataMemory dataMemory) {
        if(controlUnit == 0){
            System.out.println("My address is the lowerAdder.ALUResult because Control Unit == 0");
            AddressDestination = lowerAdder.ALUResult;
        }
        else if (controlUnit == 1){
            System.out.println("My address is the dataMemory.readData ALURes address because Control Unit == 1");
            AddressDestination = dataMemory.readData;
        }
    }


}
