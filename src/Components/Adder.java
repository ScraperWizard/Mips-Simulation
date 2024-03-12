package Components;

import Compiler.Register.Register;

public class Adder {
    Register register;
    int currentAddress;
    int nextAddress;
    private ProgramCounter pc;
    public Adder(ProgramCounter pc) {
        this.pc = pc;
    }

    public void update() {
        incrementedAddress();
    }

    public void incrementedAddress(){
        nextAddress = pc.getCounter() + 4;
        System.out.println("Adder: I returned "+nextAddress);
    }
}
