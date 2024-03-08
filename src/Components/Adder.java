package Components;

import Compiler.Register.Register;

public class Adder {
    Register address;
    public Adder() {}
    public Adder (Register address){
        this.address = address;
    }
    public int incrementedAddress(){
        System.out.println("Adder: I returned the address+4");
        return address.getAddressId()+4;
    }
}
