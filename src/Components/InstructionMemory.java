//Hessa 
package Components;

//Info from other classes
import Compiler.MipsInstruction;
import Compiler.InstructionParser;
import Compiler.Compiler;
import Compiler.Register.RegisterProvider;

public class InstructionMemory {
    private MipsInstruction[] memory;
    private Compiler compiler;
    private RegisterProvider addressProvider;
    private ProgramCounter programCounter;
    // Here Let's assume that the size for instruction memory
    private static final int MEMORY_SIZE = 1077;

    // Constructor 
    public InstructionMemory(Compiler compiler, RegisterProvider addressProvider, ProgramCounter programCounter) {
        this.compiler = compiler;
        this.addressProvider = addressProvider;
        this.memory = new MipsInstruction[MEMORY_SIZE];
        this.programCounter = programCounter;
    }

//load instruction into memory

public void loadInstruction(int address, String instructionText) {
    InstructionParser parser = new InstructionParser(instructionText, compiler, addressProvider);
    MipsInstruction instruction = parser.parse();
    //Here i want to check if the address is valid
    if (address >= 0 && address < MEMORY_SIZE) {
        memory[address] = instruction;// otherwise 
    } 
    //throw an exception 
    else {
        throw new IndexOutOfBoundsException("Instruction address exceeds memory limits :)");
    }
}
//fetch instruction from memory pc 

public MipsInstruction fetchNextInstruction(boolean isBranch, int branchAddress) {
    int address = isBranch ? branchAddress : programCounter.getCounter();

    if (address >= 0 && address < MEMORY_SIZE) {
        MipsInstruction instruction = memory[address];

        // Increment the program counter only if not branching
        if (!isBranch) {
            programCounter.increment();
        } else {
            // Update the program counter to the branch address
            programCounter.setCounter(branchAddress);
        }

        return instruction;
    } else {
        throw new IndexOutOfBoundsException("Instruction address exceeds memory limits :( ");
    }
}

}