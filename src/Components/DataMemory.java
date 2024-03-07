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
    }

    private void dataMemoryOperation(){

    }

}
