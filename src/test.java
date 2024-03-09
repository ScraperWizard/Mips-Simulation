import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;
import Components.*;
import Compiler.Compiler;
import Compiler.InstructionParser;
import Compiler.MipsInstruction;
import Compiler.RTypeMipsInstruction;


public class test {

    public static void main (String[] args) {
        String addCommand = "slt $s1, $t2, $t3";
        Compiler mipsCompiler = new Compiler();
        RegisterProvider registerProvider = new RegisterProvider();

        InstructionParser mipsInstructionParser = new InstructionParser(addCommand, mipsCompiler, registerProvider);
        MipsInstruction instructionToTest = mipsInstructionParser.parse();
        registerProvider.getRegisterByHumanName("$t2").setValue(9);
        registerProvider.getRegisterByHumanName("$t3").setValue(6);
        registerProvider.getRegisterByHumanName("$s1").setValue(0);

        ControlUnit controlUnit = new ControlUnit();
        AluControl aluControl = new AluControl();
        RegisterMemory registerMemory = new RegisterMemory();
        SignExtend signExtend = new SignExtend();
        LowerAdder ALU = new LowerAdder();




        //add s1 s2 s3
        RTypeMipsInstruction rTypeInstruction = (RTypeMipsInstruction) instructionToTest;
        controlUnit.update(rTypeInstruction.getOpCode());
        aluControl.update(controlUnit, rTypeInstruction);
        registerMemory.readData1 = 9;
        registerMemory.readData2 = 6;
        signExtend.input = 16;
        signExtend.update(rTypeInstruction);
        Multiplexer ALUSrcMUX = new Multiplexer("ALUSrcMUX", controlUnit, registerMemory, signExtend);
        ALU.update(aluControl, registerMemory, ALUSrcMUX);

        String addCommand2 = "sub $s1, $t2, $t3";
        InstructionParser mipsInstructionParser2 = new InstructionParser(addCommand2, mipsCompiler, registerProvider);
        MipsInstruction instructionToTest2 = mipsInstructionParser2.parse();
        registerProvider.getRegisterByHumanName("$t2").setValue(9);
        registerProvider.getRegisterByHumanName("$t3").setValue(6);
        registerProvider.getRegisterByHumanName("$s1").setValue(0);

        RTypeMipsInstruction rTypeInstruction2 = (RTypeMipsInstruction) instructionToTest2;
        controlUnit.update(rTypeInstruction2.getOpCode());
        aluControl.update(controlUnit, rTypeInstruction2);
        registerMemory.readData1 = 9;
        registerMemory.readData2 = 6;
        signExtend.input = 16;
        signExtend.update(rTypeInstruction2);
        Multiplexer ALUSrcMUX2 = new Multiplexer("ALUSrcMUX", controlUnit, registerMemory, signExtend);
        ALU.update(aluControl, registerMemory, ALUSrcMUX2);


        ProgramCounter programCounter = new ProgramCounter();
        ShiftLeft2 shiftLeft2 = new ShiftLeft2();
        Adder adder = new Adder();
        UpperAdd upperAdd= new UpperAdd();

        ALU.zeroFlag=1;
        AndGate andGate = new AndGate();



        adder.update(programCounter);
        shiftLeft2.update(signExtend);
        upperAdd.update(shiftLeft2,adder);
        andGate.update(controlUnit,ALU);

        Multiplexer PCSRC = new Multiplexer("PCSRC",andGate,upperAdd,adder);







    }
    }

