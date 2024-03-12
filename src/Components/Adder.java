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
        System.out.println("Adder: I returned the address + 4");
        nextAddress = pc.getCounter() + 4;
    }
}
