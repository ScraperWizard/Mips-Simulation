package Components;


public class Multiplexer {

    private String humanNickname;// PCSrcMUX, MemToRegMUX, ALUSrcMUX, RegDstMUX --> Agreed MUXS names
    int controlUnit;
    int input1;
    int input2;
    int output;

    public Multiplexer(String humanNickname, int controlUnit, int input1, int input2) {
        this.humanNickname = humanNickname;
        this.controlUnit = controlUnit;
        this.input1 = input1;
        this.input2 = input2;
    }

    public void MUXDecider (){
        if(humanNickname.equalsIgnoreCase("PCSrc")){
            System.out.println("I chose PCSrc, because I recieved PCSrc as human nickname");
            PCSrcMUX(controlUnit, input1, input2);
        }
    }

    // PSCrcMUX
    public void PCSrcMUX (int controlUnit, int input1, int input2){
        UpperAdd upperAdd = new UpperAdd();
        Adder adder = new Adder();
        if(controlUnit == 0){
            output = adder.incrementedAddress();
        }
        else if (controlUnit == 1){
            output = upperAdd.UpperAddOperation();
        }
    }
}
