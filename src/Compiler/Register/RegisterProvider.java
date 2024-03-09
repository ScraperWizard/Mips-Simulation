package Compiler.Register;


import Compiler.Addresses.AddressProvider;

public class RegisterProvider {
    private Register[] RegisterArray;
    private AddressProvider addressProvider;

    public RegisterProvider(AddressProvider addressProvider) {
        this.addressProvider = addressProvider;
        this.RegisterArray = this.generateRegisterArray();
    }

    private Register[] generateRegisterArray() {
        String[] registers = new String[]{"$0", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3", "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"};
        Register[] RegisterArray = new Register[registers.length * 4];
        for (int i = 0; i < registers.length; i++) {
            Register newRegister = new Register(registers[i], i, this.addressProvider.getAddressByShifted(i));
            for(int j = 0; j < 4; j++) {
                RegisterArray[i * 4 + j] = newRegister;
            }
        }
        return RegisterArray;
    }

    public Register[] getRegisterArray() {
        return RegisterArray;
    }

    public Register getRegisterById(int id) {
        return this.RegisterArray[id * 4];
    }

    public Register getRegisterByHumanName(String name) {
        for(int i = 0; i < this.RegisterArray.length; i+=4) {
            if(this.RegisterArray[i].getRegisterHumanName().equals(name)) {
                return this.RegisterArray[i];
            }
        }
        
        return null;
    }
}
