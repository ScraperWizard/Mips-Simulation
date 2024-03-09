package Components;

import Compiler.Addresses.AddressProvider;

import java.util.ArrayList;

public class DataMemory {
    private ControlUnit controlUnitl;
    private LowerAdder lowerAdder;
    private RegisterMemory registerMemory;
    private AddressProvider addressProvider;
    public int valueAtReadData;

    DataMemory(ControlUnit controlUnit, LowerAdder lowerAdder, RegisterMemory registerMemory, AddressProvider addressProvider) {
        this.controlUnitl = controlUnit;
        this.registerMemory = registerMemory;
        this.lowerAdder = lowerAdder;
        this.addressProvider = addressProvider;
    }

    private void dataMemoryOperation() {
        int memoryWrite  = this.controlUnitl.MemWrite;
        int memoryRead = this.controlUnitl.MemRead;
        int lowerAdderOutput = this.lowerAdder.output;

        if (memoryWrite == 1 && memoryRead == 0) {
            System.out.println("SW Instruction, so address = writeData");
            addressProvider.getAddressAtIndex(lowerAdderOutput).setValue(registerMemory.readData2);
            System.out.println("sw instruction, setting value " + registerMemory.readData2 + " in address " + lowerAdderOutput);
        } else {
            System.out.println("LW Instruction, so readData = address");
            valueAtReadData = addressProvider.getAddressAtIndex(lowerAdderOutput).getValue();
        }
    }
}
