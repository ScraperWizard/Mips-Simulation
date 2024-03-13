import Compiler.Addresses.Address;
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
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Initialize all variables needed
        AddressProvider addressProvider = new AddressProvider();
        RegisterProvider registerProvider = new RegisterProvider();
        GalaxyCompilerV2 gui = new GalaxyCompilerV2(addressProvider, registerProvider);
            int sum=0;
            for(int i=0;i<10;i++){
                sum+=i;
            }
        System.out.println(sum);

        // Compiler
        Compiler mipsCompiler = new Compiler();
        InstructionParser instructionParser = new InstructionParser(mipsCompiler, registerProvider);

        // Data path
        DataPath dataPath = new DataPath(addressProvider, registerProvider);

        gui.launchGui();

        gui.onExecuteCode((String[] codeObject) -> {
            dataPath.programCounter.setCounter(0);
            String codeInput = codeObject[0];
            String registerInput = codeObject[1];
            String[] splittedString = codeInput.split("\n");
            HashMap<String, Integer> map = new HashMap<String, Integer>();

            // Loop over all code, and search for labels
            for(int i = 0; i < splittedString.length; i++) {
                if(splittedString[i].contains(":") && splittedString[i].split(" ").length == 1) {
                    map.put(splittedString[i], i * 4 + 4);
                    System.out.println("Added label " + splittedString[i]);
                }
            }

            instructionParser.setLabelMap(map);

            RegisterProvider.initilizeRegisterValuesFromInput(registerInput, registerProvider);

            for(int i = 0; i < splittedString.length; i++)  {
                if(splittedString[i].contains(":")) {
                    continue;
                }

                try {
                    MipsInstruction mipsInstruction = instructionParser.parse(splittedString[i]);
                    printInstructionInfo(mipsInstruction);
                    dataPath.executeInstruction(mipsInstruction);

                    gui.updateRegisterValues();
                    i=dataPath.programCounter.getCounter() / 4 - 1;
                } catch (Exception e) {
                    gui.showNotification(e.getMessage());
                    e.printStackTrace(); // Print the exception details (optional, for debugging)
                    break;
                }
            }
        });

        gui.onClearRegisters((string) -> {
            Register[] registerArray = registerProvider.getRegisterArray();
            Address[] addressArray = addressProvider.getAddressArray();

            for(int i = 0; i < registerArray.length; i++) {
                registerArray[i].setValue(0);
            }

            for(int i = 0; i < addressArray.length; i++) {
                addressArray[i].setValue(0);
            }

            gui.updateRegisterValues();
            gui.showNotification("All registers and addresses have been cleared");
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

        if(instruction instanceof RTypeMipsInstruction) {
            RTypeMipsInstruction RtypeInstruction  = (RTypeMipsInstruction) instruction;
            registerArguments += " rs=" +
                    "[name=" + instruction.getSourceAddress().getRegisterHumanName()
                    + " value=" +instruction.getSourceAddress().getValue()
                    +   "]";

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
            registerArguments += " rs=" +
                    "[name=" + instruction.getSourceAddress().getRegisterHumanName()
                    + " value=" +instruction.getSourceAddress().getValue()
                    +   "]";

            registerArguments += " rt=" +
                    "[name=" + instruction.getTargetAddress().getRegisterHumanName()
                    + " value=" +instruction.getTargetAddress().getValue()
                    +   "]";
        }

        if(instruction instanceof JTypeMipsInstruction) {
            JTypeMipsInstruction JtypeInstruction  = (JTypeMipsInstruction) instruction;

            registerArguments += " jumpRegValue=" + JtypeInstruction.getAddressToJump();
        }

        registerArguments += "]";

        System.out.println(registerArguments);
        System.out.println("---------------------------");
    }
}