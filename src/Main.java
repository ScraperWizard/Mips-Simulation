import Compiler.Address.Address;
import Components.Multiplexer;
import Components.ProgramCounter;
import DataPath.DataPath;
import Compiler.Compiler;
import Compiler.InstructionParser;
import Compiler.MipsInstruction;
import Compiler.Address.AddressProvider;
import Compiler.MipsInstruction;
import Compiler.InstructionType;
import Compiler.RTypeMipsInstruction;

public class Main {
    public static void main(String[] args) {
        DataPath dataPath = buildDataPath();
        String addCommand = "add $s1, $t2, $t3";
        Compiler mipsCompiler = new Compiler();
        AddressProvider addressProvider = new AddressProvider();

        InstructionParser mipsInstructionParser = new InstructionParser(addCommand, mipsCompiler, addressProvider);
        MipsInstruction instructionToTest = mipsInstructionParser.parse();

        if(instructionToTest instanceof RTypeMipsInstruction) {
            RTypeMipsInstruction rTypeInstruction = (RTypeMipsInstruction) instructionToTest;
            System.out.println("Decoded command: opCode=" + rTypeInstruction.getOpCode()
                    + " rs=" + rTypeInstruction.getSourceAddress().getAddressHumanName()
                    + " rt="+ instructionToTest.getTargetAddress().getAddressHumanName()
                    + " rd=" + rTypeInstruction.getDestinationAddress().getAddressHumanName()
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
        ProgramCounter programCounter = new ProgramCounter();
//        Multiplexer mux1 = new Multiplexer("PCsrc");

        return new DataPath(); // TODO not real implementation
    }
}