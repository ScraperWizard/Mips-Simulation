package Components;

import Compiler.Register.Register;

public class Adder {
    Register register;
    int currentAddress;
    int nextAddress;
    public Adder() {}

    public void update (ProgramCounter pc){
        currentAddress=pc.getCounter();
        incrementedAddress();
    }

    public void incrementedAddress(){
        System.out.println("Adder: I returned the address+4");
        nextAddress= currentAddress+4;
    }
}
