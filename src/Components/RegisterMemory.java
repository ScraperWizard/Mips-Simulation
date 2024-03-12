// Hesssa
package Components;
import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;
import Compiler.RTypeMipsInstruction;
import Compiler.ITypeMipsInstruction;
import Compiler.MipsInstruction;

import java.security.PublicKey;

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

    public RegisterMemory(){
    }

    public void update(Multiplexer memToReg,ControlUnit controlUnit){
        this.memToReg = memToReg;
        this.controlUnit = controlUnit;
    }

    public void readFromRegisters(MipsInstruction instruction) {
        if(instruction instanceof RTypeMipsInstruction)
            readFromRegisters((RTypeMipsInstruction) instruction);
        else if (instruction instanceof ITypeMipsInstruction)
            readFromRegisters((ITypeMipsInstruction) instruction);
    }

    // Method to read data
    public void readFromRegisters(RTypeMipsInstruction instruction) {
        this.readData1 = instruction.getSourceAddress().getValue();
        this.readData2 = instruction.getTargetAddress().getValue();
        this.readData2Register = instruction.getTargetAddress();

        System.out.println("[RegisterMemory: Reading from" +
                "source register=" + instruction.getSourceAddress().getRegisterHumanName() + " value=" + this.readData1
                + "target register=" + instruction.getTargetAddress().getRegisterHumanName() + " value=" + this.readData2
                + "]");
    }

    public void readFromRegisters(ITypeMipsInstruction instruction) {
        this.readData1 = instruction.getSourceAddress().getValue();
        this.readData2 = instruction.getTargetAddress().getValue();
        this.readData2Register = instruction.getTargetAddress();
    }

    // Method to write data to register
    public void writeToRegister(RTypeMipsInstruction instruction) {
        System.out.println("[RegisterMemory: Writing to register=" + instruction.getDestinationAddress().getValue() +  "Value= " + memToReg.AddressDestination + "]");
        instruction.getDestinationAddress().setValue(memToReg.AddressDestination);
    }

    public void writeToRegister(ITypeMipsInstruction instruction) {
        instruction.getTargetAddress().setValue(memToReg.AddressDestination);
    }

    public void writeToRegister() {
        if(controlUnit.RegWrite==0){
            return;
        }
        else if(controlUnit.MemToReg== 0) {
            System.out.println(" Value from ALU:"+memToReg.AddressDestination);
            if(controlUnit.getInstruction() instanceof RTypeMipsInstruction) {
                RTypeMipsInstruction instruction = (RTypeMipsInstruction) controlUnit.getInstruction();

                instruction.getDestinationAddress().setValue(memToReg.AddressDestination);
            }
           else if(controlUnit.getInstruction() instanceof ITypeMipsInstruction) {
                ITypeMipsInstruction instruction = (ITypeMipsInstruction) controlUnit.getInstruction();

                instruction.getTargetAddress().setValue(memToReg.AddressDestination);
            }
        } else if(controlUnit.MemToReg==1) {
            ITypeMipsInstruction instruction = (ITypeMipsInstruction) controlUnit.getInstruction();
            instruction.getTargetAddress().setValue(memToReg.AddressDestination);

            //todo

        }
    }
}