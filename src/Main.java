import Components.Multiplexer;
import Components.ProgramCounter;
import DataPath.DataPath;
import DataPath.Wire.Wire;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to our MIPS assembly compiler. Please check the manual on how to compile your code");
        System.out.println("Please enter one single instruction");
    }

    public DataPath buildDataPath() { // This method should build all of the components,wires of a datapath
        //
        ProgramCounter programCounter = new ProgramCounter();
        Multiplexer mux1 = new Multiplexer("PCsrc");
        Wire muxToProgramCounter = new Wire(new byte[32]);

        return new DataPath(); // TODO not real implementation
    }
}