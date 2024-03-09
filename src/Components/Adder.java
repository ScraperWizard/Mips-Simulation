package Components;

import Compiler.Register.Register;

public class Adder {
    Register register;
    public Adder() {}

    public void update (Register register){
        this.register = register;
    }

    public int incrementedAddress(){
        System.out.println("Adder: I returned the address+4");
        return register.getRegisterId()+4;
    }
}
