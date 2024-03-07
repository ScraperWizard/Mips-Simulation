package Components;


public class Multiplexer {

    private String humanNickname;// PCSrcMUX, MemToRegMUX, ALUSrcMUX, RegDstMUX --> Agreed MUXS names
    int controlUnit;
    int input1;
    int input2;
    int output;

    public Multiplexer(int controlUnit){
        this.controlUnit = controlUnit;
    }

    public Multiplexer(String humanNickname) {
        this.humanNickname = humanNickname;
    }
    public Multiplexer(String humanNickname, int controlUnit) {
        this.humanNickname = humanNickname;
        this.controlUnit = controlUnit;
        MUXDecider ();
    }

    public void MUXDecider (){
        if(humanNickname.equalsIgnoreCase("PCSrc")){
            System.out.println("I chose PCSrc, because I recieved PCSrc as human nickname");
            PCSrcMUX();
        }
    }

    // PSCrcMUX
    public int PCSrcMUX (){
        UpperAdd upperAdd = new UpperAdd();
        Adder adder = new Adder();
        if(controlUnit == 0){
            System.out.println("My address is the incremented address because Control Unit == 0");
            output = adder.incrementedAddress();
        }
        else if (controlUnit == 1){
            System.out.println("My address is the UpperAdd ALURes address because Control Unit == 1");
            output = upperAdd.UpperAddOperation();
        }
        return output;
    }
}
