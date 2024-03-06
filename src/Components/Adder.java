package Components;

import Compiler.Address.Address;

public class Adder {
    Address address;
    public Adder() {}
    public Adder (Address address){
        this.address = address;
    }
    public int incrementedAddress(){
        System.out.println("Adder: I returned the address+4");
        return address.getAddressId()+4;
    }
}
