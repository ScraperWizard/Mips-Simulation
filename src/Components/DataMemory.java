package Components;

public class DataMemory {
    int readData; //output
    int memoryWrite; //input
    int memoryRead; //input
    int address; //input
    int writeData; //input

    DataMemory(ControlUnit controlUnit, LowerAdder lowerAdder, RegisterMemory registerMemory){
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
            readData = address;
        }
        // sw
        else if (memoryWrite == 1 && memoryRead == 0){
            System.out.println("SW Instruction, so address = writeData");
             address = writeData;
        }
        else if (memoryWrite == 0 && memoryRead == 0){
            System.out.println("R type instruction, IDC");
        }

    }

}
