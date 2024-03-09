package Compiler.Register;

import Compiler.Addresses.Address;

public class Register {
    private int RegisterId;
    private String RegisterHumanName;
    private int value;
    private Address address;
    public Register(String RegisterHumanName, int RegisterId, Address address) {
        this.RegisterId = RegisterId;
        this.RegisterHumanName = RegisterHumanName;
        this.address = address;
        this.value = 0;
    }

    // $s1, address = 4
    // $s1.getValue(), 4.getValue()

    public int getRegisterId() {
        return RegisterId;
    }

    public String getRegisterHumanName() {
        return RegisterHumanName;
    }

    public int getValue() {
        return this.address.getValue();
    }

    public void setValue(int v){
        this.value = v;
        this.address.setValue(v);
    }

    public Address getAddressAtRegister() {
        return this.address;
    }

    public void setAddressAtRegister(Address address) {
        this.address = address;
    }
}
