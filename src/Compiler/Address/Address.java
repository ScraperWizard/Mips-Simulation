package Compiler.Address;

public class Address {
    private String addressId;
    private String value;
    public Address(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressId() {
        return addressId;
    }

    public String getValue() {
        return value;
    }
}
