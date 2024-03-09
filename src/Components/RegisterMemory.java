//Hessssa 
package Components;

public class RegisterMemory {
    private int[] registers;
    private static final int NUM_REGISTERS = 32; // why ? because Mips has 32 Rgs
    public int readData1; // Output
    public int readData2; //Output
    
    // time for another Constructor
    public RegisterMemory() {
        this.registers = new int[NUM_REGISTERS];
        // Register 0 is always 0 in MIPS, according to Stackflow 
        //other regis 
        this.registers[0] = 0; 
    }

    // Method to read data 
    public void readFromRegisters(int readReg1, int readReg2) {
        if (readReg1 >= 0 && readReg1 < NUM_REGISTERS) {
            readData1 = registers[readReg1];
        } else {
            throw new IllegalArgumentException("Invalid read register 1");
        }
if (readReg2 >= 0 && readReg2 < NUM_REGISTERS) {
    readData2 = registers[readReg2];
} else {
    throw new IllegalArgumentException("Invalid read register 2");
}
    }
// Method to write data to  register
public void writeToRegister(int writeReg, int data, boolean writeEnable) {
    if (writeEnable && writeReg > 0 && writeReg < NUM_REGISTERS) {
        registers[writeReg] = data;
    } else if (writeReg == 0) {
    } else {
        throw new IllegalArgumentException("Invalid Writre register");
    }
}
}