package Compiler;

import Compiler.Register.RegisterProvider;

import java.util.ArrayList;
import java.util.Arrays;

import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;

import java.util.HashMap;
import java.util.regex.*;

// TODO currently this class does not validate $zero and other specific edge cases handeling of syntax errors
public class InstructionParser {
    Compiler compiler;
    ArrayList<String> registers;
    RegisterProvider addressProvider;
    private HashMap<String, Integer> labelMap;
    public InstructionParser(Compiler compiler, RegisterProvider registerProvider) {
        this.compiler = compiler;
        this.registers = new ArrayList<String>();
        this.addressProvider = registerProvider;
    }

    public void setLabelMap(HashMap<String, Integer> labelMap) {
        this.labelMap = labelMap;
    }

    public MipsInstruction parse(String instruction) {
        if(instruction.contains(":")) {
            return null;
        }

        if(!validateInstructionKeyword(instruction)) {
            throw new IllegalArgumentException("Invalid MIPS instruction keyword " + this.parseKeyword(instruction));
        }

        if(!validateInstructionSyntax(instruction)) {
            throw new IllegalArgumentException("Invalid MIPS syntax, please review your syntax");
        }

        InstructionType typeOfInstruction = this.getInstructionType(instruction);
        String[] registersInInstruction = this.parseRegisters(instruction);
        int opCodeOfInstruction = this.getInstructionOpCode(instruction);
        int functionCodeOfInstruction = this.getInstructionFunctionCode(instruction);
        Command instructionCommand = this.compiler.getCommandByName(this.parseKeyword(instruction));

        System.out.println("Parsed registers " + Arrays.toString(registersInInstruction));

        if(typeOfInstruction == InstructionType.Rtype) {
            Register destinationAddress = addressProvider.getRegisterByHumanName(registersInInstruction[0]);
            Register sourceAddress = addressProvider.getRegisterByHumanName(registersInInstruction[1]);
            Register targetAddress = addressProvider.getRegisterByHumanName(registersInInstruction[2]);
            return new RTypeMipsInstruction(opCodeOfInstruction, sourceAddress, targetAddress, destinationAddress, 0, functionCodeOfInstruction, instructionCommand);
        } else if(typeOfInstruction == InstructionType.Itype) {
            int constantValue = getConstantValueIInstruction(instruction);
            Register sourceAddress = addressProvider.getRegisterByHumanName(registersInInstruction[1]);
            Register targetAddress = addressProvider.getRegisterByHumanName(registersInInstruction[0]);

            System.out.println("Constant value " + constantValue);

            return new ITypeMipsInstruction(opCodeOfInstruction, sourceAddress, targetAddress, functionCodeOfInstruction, instructionCommand, constantValue);
        } else if(typeOfInstruction == InstructionType.Jtype) {
            int jumpRegValue;

            if(registersInInstruction.length > 0) {
                jumpRegValue = addressProvider.getRegisterByHumanName(registersInInstruction[0]).getValue();
            } else {
                System.out.println("Inside of J type instruction " + instruction + " " + instruction.contains(":"));

                try {
                    int value = Integer.parseInt(instruction.split(" ")[1]);
                    jumpRegValue = value;
                } catch (NumberFormatException e) {
                    System.out.println(instruction.split(" ")[1]);
                    jumpRegValue = this.labelMap.get(instruction.split(" ")[1]+":");
                }
            }

            return new JTypeMipsInstruction(opCodeOfInstruction, functionCodeOfInstruction, instructionCommand, jumpRegValue);
        };

        throw new IllegalArgumentException("Unsupported mips instruction type");
    }

    private int getConstantValueIInstruction(String instruction) {
        if(!instruction.contains("(")) {
            try {
                return Integer.parseInt(instruction.split(", ")[2]);
            } catch (NumberFormatException e) {
                int res = this.labelMap.get(instruction.split(", ")[2] + ":");

                if(this.parseKeyword(instruction).equals("beq")) {
                    res-=4;
                    res/=4;
                }

                return res;
            }
        } else {
            String s1 = instruction.split(",")[1];
            String s2 = s1.split("\\(")[0];
            return Integer.parseInt(s2.split("")[1]);
        }
    }

    private int getInstructionOpCode(String instruction) {
        return this.compiler.getOpCodeByCommandName(this.parseKeyword(instruction));
    }

    private int getInstructionFunctionCode(String instruction) {
        return this.compiler.getFunctionCodeByCommandName(this.parseKeyword(instruction));
    }

    private InstructionType getInstructionType(String instruction) {
        String type = this.compiler.getTypeByCommandName(this.parseKeyword(instruction));
        if(type.equals("I")) {
            return InstructionType.Itype;
        } else if(type.equals("R")) {
            return InstructionType.Rtype;
        } else {
            return InstructionType.Jtype;
        }
    }

    private boolean validateInstructionSyntax(String instruction) {
        // Count spaces and commas in the instruction
        int spaceCount = instruction.length() - instruction.replace(" ", "").length();
        int commaCount = instruction.length() - instruction.replace(",", "").length();

        // Check if the syntax meets the specified criteria
        if (((spaceCount != 2 && spaceCount != 3) || (commaCount != 1 && commaCount != 2)) && instruction.split(" ").length >= 3) {
            throw new IllegalArgumentException("Error in mips instruction syntax, check your commas spaces.");
        }

        String[] registers = this.parseRegisters(instruction);

        if(!this.validateRegisters(registers)) {
            throw new IllegalArgumentException("Error in mips registers syntax");
        }

        return true;
    }

    private boolean validateRegister(String register) {
        boolean result = false;
        Register[] addresses = this.addressProvider.getRegisterArray();

        for(int i = 0; i < addresses.length; i++) {
            if(addresses[i].getRegisterHumanName().equals(register)) {
                result = true;
            }
        }

        return result;
    }

    private boolean validateRegisters(String[] registers) {
        boolean result = true;

        for(int i = 0; i < registers.length && result; i++) {
            this.registers.add(registers[i]);

            if(!this.validateRegister((registers[i]))) {
                result = false;
            }
        }

        return result;
    }

    private String[] parseRegisters(String instruction) {
        ArrayList<String> registersList = new ArrayList<>();
        String regex = "\\$([\\w\\d]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(instruction);

        while (matcher.find()) {
            String register = matcher.group(1);
            registersList.add("$" + register);
        }

        return registersList.toArray(new String[0]);
    }

    private boolean validateInstructionKeyword(String instruction) {
        String[] validInstructionKeywords = compiler.getValidKeyWordInstructions();
        String keywordFromInstruction = this.parseKeyword(instruction);

        for (int i = 0; i < validInstructionKeywords.length; i++) {
            if (keywordFromInstruction.equals(validInstructionKeywords[i])) {
                return true; // Keyword is valid
            }
        }

        return false; // Keyword is not valid
    }
    private String parseKeyword(String instruction) {
        if(instruction.length() == 0 || !instruction.contains(" ")) {
            throw new IllegalArgumentException("Unable to parse keyword in instruction " + instruction);
        }

        return instruction.split(" ")[0];
    }
}
