package Compiler.Addresses;

import Compiler.Register.Register;

public class Address {
    private int value;
    private int addressId;

    public Address(int addressId) {
        this.addressId = addressId;
        this.value = Integer.MAX_VALUE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getAddressId() {
        return addressId;
    }
}
