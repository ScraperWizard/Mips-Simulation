package Components;


public class Multiplexer {

    private String humanNickname;// PCSrcMUX, MemToRegMUX, ALUSrcMUX, RegDstMUX --> Agreed MUXS names
    int controlUnit;
    int AddressDestination;

    public Multiplexer(int controlUnit){
        this.controlUnit = controlUnit;
    }

    public Multiplexer(String humanNickname) {
        this.humanNickname = humanNickname;
    }
    //PCSrcMUX
    public Multiplexer(String humanNickname, AndGate andGate, UpperAdd givenUpperAdd, Adder givenAdder) {
        this.humanNickname = humanNickname;
        this.controlUnit = andGate.controlUnitForPCSrc;
        MUXDecider(givenUpperAdd, givenAdder);
    }

    private void MUXDecider (UpperAdd givenUpperAdd, Adder givenAdder){
        if(humanNickname.equalsIgnoreCase("PCSrc")){
            System.out.println("I chose PCSrc, because I received PCSrc as human nickname");
            PCSrcMUX(givenUpperAdd, givenAdder);
        }
    }

    // PSCrcMUX
    private int PCSrcMUX (UpperAdd givenUpperAdd, Adder givenAdder){

        if(controlUnit == 0){
            System.out.println("My address is the incremented address because Control Unit == 0");
            AddressDestination = givenAdder.incrementedAddress();
        }
        else if (controlUnit == 1){
            System.out.println("My address is the UpperAdd ALURes address because Control Unit == 1");
            AddressDestination = givenUpperAdd.ALUResult;
        }
        return AddressDestination;
    }
}
