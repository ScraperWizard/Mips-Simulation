package Compiler.Address;

public class Address {
    private int addressId;
    private String addressHumanName;
    private int value;
    public Address(String addressHumanName, int addressId) {
        this.addressId = addressId;
        this.addressHumanName = addressHumanName;
        this.value = 0;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddressHumanName() {
        return addressHumanName;
    }

    public int getValue() {
        return value;
    }
}
