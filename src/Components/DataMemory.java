package Components;

import Compiler.Addresses.AddressProvider;

import java.util.ArrayList;

public class DataMemory {
    private ControlUnit controlUnitl;
    private LowerAdder lowerAdder;
    private RegisterMemory registerMemory;
    private AddressProvider addressProvider;
    public int valueAtReadData;

    public DataMemory(ControlUnit controlUnit, LowerAdder lowerAdder, RegisterMemory registerMemory, AddressProvider addressProvider) {
        this.controlUnitl = controlUnit;
        this.registerMemory = registerMemory;
        this.lowerAdder = lowerAdder;
        this.addressProvider = addressProvider;
    }

    public  DataMemory(){

    }
    public void update(ControlUnit controlUnit, LowerAdder lowerAdder, RegisterMemory registerMemory, AddressProvider addressProvider) {
        this.controlUnitl = controlUnit;
        this.registerMemory = registerMemory;
        this.lowerAdder = lowerAdder;
        this.addressProvider = addressProvider;
        dataMemoryOperation();
    }

    private void dataMemoryOperation() {
        int memoryWrite  = this.controlUnitl.MemWrite;
        int memoryRead = this.controlUnitl.MemRead;
        int lowerAdderOutput = this.lowerAdder.output;
        System.out.println("Data Memory:");

        if (memoryWrite == 1 && memoryRead == 0) {
            addressProvider.getAddressAtIndex(lowerAdderOutput).setValue(registerMemory.readData2);
            System.out.println("SW instruction, setting value " + registerMemory.readData2 + " in address " + lowerAdderOutput);
        } else if(memoryRead==1 && memoryWrite==0) {
            System.out.println("LW Instruction, loading the value in "  +lowerAdderOutput );
            valueAtReadData = addressProvider.getAddressAtIndex(lowerAdderOutput).getValue();
        }
        else{
            System.out.println("data memory has no use");
        }
    }
}
