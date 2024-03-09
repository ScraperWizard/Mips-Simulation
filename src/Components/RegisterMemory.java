// Hesssa
package Components;
import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;
import Compiler.RTypeMipsInstruction;
import Compiler.ITypeMipsInstruction;

public class RegisterMemory {
    private RegisterProvider registerProvider;
    private Multiplexer memToReg;
    private ControlUnit controlUnit;
    public int readData1;
    public int readData2;
    public Register readData2Register;

    public RegisterMemory(Multiplexer memToReg, ControlUnit controlUnit) {
        this.memToReg = memToReg;
        this.controlUnit = controlUnit;
    }

    // Method to read data
    public void readFromRegisters(RTypeMipsInstruction instruction) {
        this.readData1 = instruction.getSourceAddress().getValue();
        this.readData2 = instruction.getTargetAddress().getValue();
        this.readData2Register = instruction.getTargetAddress();
    }

    public void readFromRegisters(ITypeMipsInstruction instruction) {
        this.readData1 = instruction.getSourceAddress().getValue();
        this.readData2 = instruction.getTargetAddress().getValue();
        this.readData2Register = instruction.getTargetAddress();
    }

    // Method to write data to register
    public void writeToRegister(RTypeMipsInstruction instruction) {
        instruction.getDestinationAddress().setValue(memToReg.AddressDestination);
    }

    public void writeToRegister(ITypeMipsInstruction instruction) {
        instruction.getTargetAddress().setValue(memToReg.AddressDestination);
    }

    public void writeToRegister() {
        if(memToReg.AddressDestination == 1) {
            if(controlUnit.getInstruction() instanceof RTypeMipsInstruction) {
                RTypeMipsInstruction instruction = (RTypeMipsInstruction) controlUnit.getInstruction();

                instruction.getDestinationAddress().setValue(memToReg.AddressDestination);
            }
        } else {
            if(controlUnit.getInstruction() instanceof ITypeMipsInstruction) {
                ITypeMipsInstruction instruction = (ITypeMipsInstruction) controlUnit.getInstruction();

                instruction.getTargetAddress().setValue(memToReg.AddressDestination);
            }
        }
    }
}