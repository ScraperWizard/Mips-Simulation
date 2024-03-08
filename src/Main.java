import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;
import Components.ControlUnit;
import Components.DataMemory;
import DataPath.DataPath;
import Compiler.Compiler;
import Compiler.InstructionParser;
import Compiler.MipsInstruction;
;
import Compiler.RTypeMipsInstruction;

public class Main {
    public static void main(String[] args) {
        DataPath dataPath = buildDataPath();
        String addCommand = "add $s1, $t2, $t3";
        Compiler mipsCompiler = new Compiler();
        RegisterProvider registerProvider = new RegisterProvider();

        InstructionParser mipsInstructionParser = new InstructionParser(addCommand, mipsCompiler, registerProvider);
        MipsInstruction instructionToTest = mipsInstructionParser.parse();

        ControlUnit controlUnit = new ControlUnit(instructionToTest.getOpCode()); //Test

        DataMemory dataMemory = new DataMemory(instructionToTest.getTargetAddress(), instructionToTest.getSourceAddress(), instructionToTest.)
//        AndGate andGate = new AndGate();

        if(instructionToTest instanceof RTypeMipsInstruction) {
            RTypeMipsInstruction rTypeInstruction = (RTypeMipsInstruction) instructionToTest;
            System.out.println("Decoded command: opCode=" + rTypeInstruction.getOpCode()
                    + " rs=" + rTypeInstruction.getSourceAddress().getRegisterHumanName()
                    + " rt="+ instructionToTest.getTargetAddress().getRegisterHumanName()
                    + " rd=" + rTypeInstruction.getDestinationAddress().getRegisterHumanName()
                    + " shamt=" +rTypeInstruction.getShamt()
                    + " functionCode=" + instructionToTest.getFunctionCode());
        }
//        int opCode = instructionToTest.getOpcode();
//        int functionCode = instructionToTest.getFunctionCode();
//        Address sourceAddress = instructionToTest.getSourceAddress();
//        Address targetAddress = instructionToTest.getSourceAddress();
    }

    public static DataPath buildDataPath() { // This method should build all of the components,wires of a datapath
        // Initialize program counter, mux
//        ProgramCounter programCounter = new ProgramCounter();
//        Multiplexer mux1 = new Multiplexer("PCsrc");

        return new DataPath(); // TODO not real implementation
    }
}