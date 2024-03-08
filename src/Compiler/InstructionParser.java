package Compiler;

import Compiler.Register.RegisterProvider;

import java.util.ArrayList;
import java.util.Arrays;

import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;

import java.util.regex.*;

// TODO currently this class does not validate $zero and other specific edge cases handeling of syntax errors
public class InstructionParser {
    String instruction;
    Compiler compiler;
    ArrayList<String> registers;
    RegisterProvider addressProvider;
    public InstructionParser(String instruction, Compiler compiler, RegisterProvider registerProvider) {
        this.instruction = instruction;
        this.compiler = compiler;
        this.registers = new ArrayList<String>();
        this.addressProvider = registerProvider;
    }

    public MipsInstruction parse() {
        if(!validateInstructionKeyword()) {
            throw new IllegalArgumentException("Invalid MIPS instruction keyword " + this.parseKeyword());
        }

        if(!validateInstructionSyntax()) {
            throw new IllegalArgumentException("Invalid MIPS syntax, please review your syntax");
        }

        InstructionType typeOfInstruction = this.getInstructionType();
        String[] registersInInstruction = this.parseRegisters();
        int opCodeOfInstruction = this.getInstructionOpCode();

        System.out.println(typeOfInstruction);

        if(typeOfInstruction == InstructionType.Rtype) {
            System.out.println(Arrays.toString(registersInInstruction));
            Register sourceAddress = addressProvider.getRegisterByHumanName(registersInInstruction[0]);
            Register targetAddress = addressProvider.getRegisterByHumanName(registersInInstruction[1]);
            Register destinationAddress = addressProvider.getRegisterByHumanName(registersInInstruction[2]);
            int functionCodeOfInstruction = this.getInstructionFunctionCode();

            return new RTypeMipsInstruction(opCodeOfInstruction, sourceAddress, targetAddress, destinationAddress, 0, functionCodeOfInstruction);
        } else if(typeOfInstruction == InstructionType.Itype) {
            return new ITypeMipsInstruction();
        };

        throw new IllegalArgumentException("Unsupported mips instruction type");
    }

    private int getInstructionOpCode() {
        return this.compiler.getOpCodeByCommandName(this.parseKeyword());
    }

    private int getInstructionFunctionCode() {
        return this.compiler.getFunctionCodeByCommandName(this.parseKeyword());
    }

    private InstructionType getInstructionType() {
        String type = this.compiler.getTypeByCommandName(this.parseKeyword());
        if(type.equals("I")) {
            return InstructionType.Itype;
        } else if(type.equals("R")) {
            return InstructionType.Rtype;
        } else {
            return InstructionType.Jtype;
        }
    }

    private boolean validateInstructionSyntax() {
        // Count spaces and commas in the instruction
        int spaceCount = instruction.length() - instruction.replace(" ", "").length();
        int commaCount = instruction.length() - instruction.replace(",", "").length();

        // Check if the syntax meets the specified criteria
        if ((spaceCount != 2 && spaceCount != 3) || (commaCount != 1 && commaCount != 2)) {
            throw new IllegalArgumentException("Error in mips instruction syntax, check your commas spaces.");
        }

        String[] registers = this.parseRegisters();

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

    private String[] parseRegisters() {
        ArrayList<String> registersList = new ArrayList<>();
        String regex = "\\$([\\w\\d]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.instruction);

        while (matcher.find()) {
            String register = matcher.group(1);
            registersList.add("$" + register);
        }

        return registersList.toArray(new String[0]);
    }

    private boolean validateInstructionKeyword() {
        String[] validInstructionKeywords = compiler.getValidKeyWordInstructions();
        String keywordFromInstruction = this.parseKeyword();

        for (int i = 0; i < validInstructionKeywords.length; i++) {
            if (keywordFromInstruction.equals(validInstructionKeywords[i])) {
                return true; // Keyword is valid
            }
        }

        return false; // Keyword is not valid
    }
    private String parseKeyword() {
        if(this.instruction.length() == 0 || !this.instruction.contains(" ")) {
            throw new IllegalArgumentException("Unable to parse keyword in instruction " + this.instruction);
        }

        return this.instruction.split(" ")[0];
    }
}
