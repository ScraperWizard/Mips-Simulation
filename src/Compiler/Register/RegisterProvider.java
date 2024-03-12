package Compiler.Register;


import Compiler.Addresses.AddressProvider;

import java.util.Arrays;

public class RegisterProvider {
    private Register[] RegisterArray;

    public RegisterProvider() {
        this.RegisterArray = this.generateRegisterArray();
    }

    private Register[] generateRegisterArray() {
        String[] registers = new String[]{"$0", "$at", "$v0", "$v1", "$a0", "$a1", "$a2", "$a3", "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$s0", "$s1", "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$t8", "$t9", "$k0", "$k1", "$gp", "$sp", "$fp", "$ra"};
        Register[] RegisterArray = new Register[registers.length * 4];
        for (int i = 0; i < registers.length; i++) {
            Register newRegister = new Register(registers[i], i);
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

    public static void initilizeRegisterValuesFromInput(String input, RegisterProvider registerProvider) {
        String[] registerInputs = input.split("\n");

        for(int i = 0; i < registerInputs.length; i++) {
            String register = registerInputs[i].split("=")[0];
            int value = Integer.parseInt(registerInputs[i].split("=")[1]);

            Register registerToInit = registerProvider.getRegisterByHumanName(register);

            if(registerToInit == null) {
                throw new Error("Unexpected register in register inputs init");
            }

            registerToInit.setValue(value);
        }
    }
}
