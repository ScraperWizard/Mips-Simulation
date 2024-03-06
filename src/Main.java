import Compiler.Address.Address;
import Components.Multiplexer;
import Components.ProgramCounter;
import DataPath.DataPath;
import Compiler.Compiler;
import Compiler.InstructionParser;
import Compiler.MipsIntruction;
import Compiler.Address.AddressProvider;

public class Main {
    public static void main(String[] args) {
//        DataPath dataPath = buildDataPath();
//        String addCommand = "add $s1, $t2, $t3";
//        Compiler mipsCompiler = new Compiler();
//        InstructionParser mipsInstructionParser = new InstructionParser(addCommand, mipsCompiler);
//        MipsIntruction instructionToStart = mipsInstructionParser.parse();
//
//        System.out.println("Your instruction is valid");
        AddressProvider addressProvider = new AddressProvider();
        Address[] addresses = addressProvider.getAddressArray();

        for(int i = 0; i < addresses.length; i+=4) {
            System.out.println(addresses[i].getAddressHumanName() + " " + addresses[i].getAddressId());
        }

    }

    public static DataPath buildDataPath() { // This method should build all of the components,wires of a datapath
        // Initialize program counter, mux
        ProgramCounter programCounter = new ProgramCounter();
        Multiplexer mux1 = new Multiplexer("PCsrc");

        return new DataPath(); // TODO not real implementation
    }
}