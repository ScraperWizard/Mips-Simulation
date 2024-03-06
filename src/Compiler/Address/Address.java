package Compiler.Address;

public class Address {
    private int addressId;
    private String value;
    public Address(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getValue() {
        return value;
    }
}
