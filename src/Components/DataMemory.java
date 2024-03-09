package Components;

import java.util.ArrayList;

public class DataMemory {
    int readData; //output
    int memoryWrite; //input
    int memoryRead; //input
    int address; //input
    int writeData; //input

    DataMemory(){    }
    public void update (ControlUnit controlUnit, LowerAdder lowerAdder, RegisterMemory registerMemory){
        this.memoryRead = controlUnit.MemRead;
        this.memoryWrite = controlUnit.MemWrite;
        this.address = lowerAdder.ALUResult;
        this.writeData = registerMemory.readData2;
        dataMemoryOperation();
    }

    private void dataMemoryOperation(){
        // lw
        if(memoryWrite == 0 && memoryRead == 1){
            System.out.println("LW Instruction, so readData = address");
            //readData = .getAddressId[address];
        }
        // sw
        else if (memoryWrite == 1 && memoryRead == 0){
            System.out.println("SW Instruction, so address = writeData");
           // .getAddressId[address] = writeData;
        }
        else if (memoryWrite == 0 && memoryRead == 0){
            System.out.println("R type instruction, IDC");
        }

    }

}
