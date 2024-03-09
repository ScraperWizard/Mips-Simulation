package Compiler.Addresses;

public class AddressProvider {
    private Address[] addressArray;
    private final int address_array_length = 400;

    public AddressProvider() {
        this.addressArray = new Address[address_array_length];

        for(int i = 0; i < address_array_length; i++) {
            this.addressArray[i] = new Address(i);
        }
    }

    public Address[] getAddressArray() {
        return addressArray;
    }

    public Address getAddressAtIndex(int index) {
        if(index >= address_array_length || index < 0) {
            throw new IllegalArgumentException("Invalid address index you can't get");
        }

        return addressArray[index];
    }

    public Address getAddressByShifted(int value) {
        return addressArray[value * 4];
    }
}
