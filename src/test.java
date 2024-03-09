import Compiler.Addresses.Address;
import Compiler.Addresses.AddressProvider;
import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;
import Components.*;
import Compiler.Compiler;
import Compiler.InstructionParser;
import Compiler.MipsInstruction;
import Compiler.RTypeMipsInstruction;

public class test {

    public static void main(String[] args) {
        String addCommand = "slt $s1, $t2, $t3";
        Compiler mipsCompiler = new Compiler();
        AddressProvider addressProvider = new AddressProvider();
        RegisterProvider registerProvider = new RegisterProvider(addressProvider);

        Register s1 = registerProvider.getRegisterByHumanName("$s1");
        Register s2 = registerProvider.getRegisterByHumanName("$s2");

        s1.setValue(5);
        s2.setValue(20);

        // sw $s1, 0($s2)
        addressProvider.getAddressAtIndex(s2.getValue()).setValue(s1.getValue());

        System.out.println("Value of s1, " + s1.getValue());
        System.out.println("Value of s2, " + s2.getValue());
        System.out.println("Value at address 20, " + addressProvider.getAddressAtIndex(20).getValue());

        // lw $s2, 0($s1)
        int valueAtS1 = addressProvider.getAddressAtIndex(s2.getValue()).getValue();
        s2.setValue(valueAtS1);

        System.out.println("Value of s1, " + s1.getValue());
        System.out.println("Value of s2, " + s2.getValue());
        System.out.println("Value at address 20, " + addressProvider.getAddressAtIndex(20).getValue());
//        Address[] arr = addressProvider.getAddressArray();
//
//        for(int i = 0; i < 50; i++) {
//            System.out.println(arr[i].getValue());
//        }

//        InstructionParser mipsInstructionParser = new InstructionParser(addCommand, mipsCompiler, registerProvider);
//        MipsInstruction instructionToTest = mipsInstructionParser.parse();
//        registerProvider.getRegisterByHumanName("$t2").setValue(9);
//        registerProvider.getRegisterByHumanName("$t3").setValue(6);
//        registerProvider.getRegisterByHumanName("$s1").setValue(0);
//
//        ControlUnit controlUnit = new ControlUnit();
//        AluControl aluControl = new AluControl();
//        RegisterMemory registerMemory = new RegisterMemory();
//        SignExtend signExtend = new SignExtend();
//        LowerAdder ALU = new LowerAdder();
//
//        // add s1 s2 s3
//        RTypeMipsInstruction rTypeInstruction = (RTypeMipsInstruction) instructionToTest;
//        controlUnit.update(rTypeInstruction.getOpCode());
//        aluControl.update(controlUnit, rTypeInstruction);
//        registerMemory.readData1 = 9;
//        registerMemory.readData2 = 6;
//        signExtend.input = 16;
//        signExtend.update(rTypeInstruction);
//        Multiplexer ALUSrcMUX = new Multiplexer("ALUSrcMUX", controlUnit, registerMemory, signExtend);
//        ALU.update(aluControl, registerMemory, ALUSrcMUX);
    }
}
