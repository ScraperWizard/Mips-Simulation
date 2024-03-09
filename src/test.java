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

    }
    }

