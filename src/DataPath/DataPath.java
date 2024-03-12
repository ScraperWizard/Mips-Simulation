package DataPath;

import Compiler.Addresses.AddressProvider;
import Compiler.Register.RegisterProvider;
import Components.*;
import Compiler.MipsInstruction;
import Compiler.JTypeMipsInstruction;
import Compiler.ITypeMipsInstruction;
import Compiler.RTypeMipsInstruction;

public class DataPath {
    private AddressProvider addressProvider;
    private RegisterProvider registerProvider;
    private Adder adder;
    public ProgramCounter programCounter;
    private LowerAdder lowerAdder;
    private ControlUnit controlUnit;
    private RegisterMemory registerMemory;
    private ShiftLeft2 shiftLeft2;
    private SignExtend signExtend;
    private UpperAdd upperAdd;
    private DataMemory dataMemory;
    private Multiplexer ALUSrc;
    private Multiplexer MemToReg;
    private Multiplexer PCSrc;
    private AndGate andGate;
    private AluControl aluControl;

    public DataPath(AddressProvider addressProvider, RegisterProvider registerProvider) {
        this.addressProvider = addressProvider;
        this.registerProvider = registerProvider;
        this.programCounter = new ProgramCounter();
        programCounter.setCounter(0);

        this.adder = new Adder(programCounter);
        this.lowerAdder = new LowerAdder();
        this.controlUnit = new ControlUnit();
        this.andGate = new AndGate(controlUnit, lowerAdder);
        this.registerMemory = new RegisterMemory();
        this.shiftLeft2 = new ShiftLeft2();
        this.signExtend= new SignExtend();
        this.upperAdd = new UpperAdd();
        this.dataMemory = new DataMemory();
        this.aluControl = new AluControl();
        this.ALUSrc = new Multiplexer("ALUSrc", controlUnit, registerMemory, signExtend);
        this.MemToReg = new Multiplexer("MemToReg", controlUnit, lowerAdder, dataMemory);
        this.PCSrc = new Multiplexer("PCsrc", andGate, upperAdd, adder);
    }



    public void executeInstruction(MipsInstruction instruction) {
        if(instruction instanceof JTypeMipsInstruction){
            JTypeMipsInstruction jTypeMipsInstruction = (JTypeMipsInstruction) instruction;
            if(jTypeMipsInstruction.getOpCode()==3){
                registerProvider.getRegisterByHumanName("$ra").setValue(programCounter.getCounter()+4);
            }
            if(jTypeMipsInstruction.getOpCode()==6){
                programCounter.setCounter(registerProvider.getRegisterByHumanName("$ra").getValue());
            }
            programCounter.setCounter(jTypeMipsInstruction.getAddressToJump());
            return;
        }
        controlUnit.update(instruction);
        adder.update();
        registerMemory.readFromRegisters(instruction);
        signExtend.update(instruction);
        Multiplexer ALUSrc = new Multiplexer("ALUSrc",controlUnit,registerMemory,signExtend);
        aluControl.update(controlUnit, instruction);
        shiftLeft2.update(signExtend);
        lowerAdder.update(aluControl, registerMemory, ALUSrc);
        upperAdd.update(shiftLeft2, adder);
        andGate.update();
        dataMemory.update(controlUnit, lowerAdder, registerMemory, addressProvider);
        Multiplexer MemToReg = new Multiplexer("MemToReg", controlUnit,lowerAdder, dataMemory);
        registerMemory.update(MemToReg,controlUnit);
        registerMemory.writeToRegister();
        Multiplexer PCSrc = new Multiplexer("PCsrc", andGate, upperAdd, adder);
        programCounter.setCounter(PCSrc.AddressDestination);
    }
}
