import Compiler.Addresses.AddressProvider;
import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;
import Components.*;
import DataPath.DataPath;
import Compiler.Compiler;
import Compiler.InstructionParser;
import Compiler.MipsInstruction;
;
import Compiler.RTypeMipsInstruction;
import Compiler.ITypeMipsInstruction;
import Compiler.JTypeMipsInstruction;
import Compiler.Command;
import GUI.GalaxyCompilerV2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Initialize all variables needed
        AddressProvider addressProvider = new AddressProvider();
        RegisterProvider registerProvider = new RegisterProvider(addressProvider);
        GalaxyCompilerV2 gui = new GalaxyCompilerV2(addressProvider, registerProvider);

        // Compiler
        Compiler mipsCompiler = new Compiler();
        InstructionParser instructionParser = new InstructionParser(mipsCompiler, registerProvider);

        // Data path
        DataPath dataPath = new DataPath(addressProvider, registerProvider);

        gui.launchGui();
        // add $s3, $s1, $s2
        // $s1=2
        // $s2=3

        gui.onExecuteCode((String[] codeObject) -> {
            String codeInput = codeObject[0];
            String registerInput = codeObject[1];
            String[] splittedString = codeInput.split("\n");

            for(int i = 0; i < splittedString.length; i++) {
                try {
                    MipsInstruction mipsInstruction = instructionParser.parse(splittedString[i]);
                    printInstructionInfo(mipsInstruction);

                    RegisterProvider.initilizeRegisterValuesFromInput(registerInput, registerProvider);
//                    dataPath.executeInstruction(mipsInstruction);

                    gui.updateRegisterValues();
                } catch (Exception e) {
                    gui.showNotification(e.getMessage());
                    e.printStackTrace(); // Print the exception details (optional, for debugging)
                }
            }
        });
    }

    public static void printInstructionInfo(MipsInstruction instruction) {
        int opCodeOfInstruction = instruction.getOpCode();
        Command instructionCommand = instruction.getCommand();
        System.out.println("----Instruction incoming----");
        System.out.println("[Instruction keyword/command:"
                + " name=" + instructionCommand.getName()
                + " opCode=" + opCodeOfInstruction
                + " type=" + instructionCommand.getType()
                + " functionCode=" + instruction.getFunctionCode()
                + "]"
        );

        String registerArguments = "[Instruction register arguments";

        registerArguments += " rs=" +
                "[name=" + instruction.getSourceAddress().getRegisterHumanName()
                + " value=" +instruction.getSourceAddress().getValue()
                +   "]";

        if(instruction instanceof RTypeMipsInstruction) {
            RTypeMipsInstruction RtypeInstruction  = (RTypeMipsInstruction) instruction;

            registerArguments += " rt=" +
                    "[name=" + instruction.getTargetAddress().getRegisterHumanName()
                    + " value=" +instruction.getTargetAddress().getValue()
                    +   "]";
            registerArguments += " rd=" +
                    "[name=" + RtypeInstruction.getDestinationAddress().getRegisterHumanName()
                    + " value=" + RtypeInstruction.getDestinationAddress().getValue()
                    +   "]";
        }

        if(instruction instanceof ITypeMipsInstruction) {
            ITypeMipsInstruction ItypeInstruction  = (ITypeMipsInstruction) instruction;

            registerArguments += " rt=" +
                    "[name=" + instruction.getTargetAddress().getRegisterHumanName()
                    + " value=" +instruction.getTargetAddress().getValue()
                    +   "]";
        }

        registerArguments += "]";

        System.out.println(registerArguments);
        System.out.println("---------------------------");
    }
}