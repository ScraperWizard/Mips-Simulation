package Compiler.Address;

public class AddressProvider {
    private Address[] addressArray;

    public AddressProvider() {
        this.addressArray = this.generateAddressArray();
    }

    private Address[] generateAddressArray() {
        String[] registers = new String[]{"$0", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3", "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"};
        Address[] addressArray = new Address[registers.length * 4];
        for (int i = 0; i < registers.length; i++) {
            Address newAddress = new Address(registers[i], i);
            for(int j = 0; j < 4; j++) {
                addressArray[i * 4 + j] = newAddress;
            }
        }
        return addressArray;
    }

    public Address[] getAddressArray() {
        return addressArray;
    }

    public Address getAddressById(int id) {
        return this.addressArray[id * 4];
    }

    public Address getAddressByHumanName(String name) {
        for(int i = 0; i < this.addressArray.length; i+=4) {
            if(this.addressArray[i].getAddressHumanName().equals(name)) {
                return this.addressArray[i];
            }
        }
        
        return null;
    }
}
