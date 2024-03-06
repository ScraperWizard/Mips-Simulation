package Compiler;

import java.util.ArrayList;
import java.util.HashMap;

// TODO currently this class does not validate $zero and other specific edge cases handeling of syntax errors
public class InstructionParser {
    String instruction;
    Compiler compiler;
    ArrayList<String> registers;
    public InstructionParser(String instruction, Compiler compiler) {
        this.instruction = instruction;
        this.compiler = compiler;
        this.registers = new ArrayList<String>();
    }

    public MipsIntruction parse() {
        if(!validateInstructionKeyword()) {
            throw new IllegalArgumentException("Invalid MIPS instruction keyword " + this.parseKeyword());
        }

        if(!validateInstructionSyntax()) {
            throw new IllegalArgumentException("Invalid MIPS syntax, please review your syntax");
        }

        InstructionType typeOfInstruction = this.getInstructionType();
        int opCodeOfInstruction = this.getInstructionOpCode();

        return new MipsIntruction() {
            @Override
            public byte[] getOpCode() {
                return super.getOpCode();
            }
        };
    }

    private int getInstructionOpCode() {
        HashMap<String, Integer> OpCodesMap = new HashMap<String, Integer>();

        return 0;
    }

    private InstructionType getInstructionType() {
        if(this.registers.size() == 1) {
            return InstructionType.Itype;
        } else if(this.registers.size() == 2) {
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
        if(!register.contains("$") || register.contains(" ")) {
            return false;
        }

        String[] parsedRegister = register.split("");
        String[] allowedLetters = new String[]{"s", "t", "r", "zero"};
        String[] allowedNumbersOrSpecialCharacters = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "p"};
        if(!parsedRegister[0].equals("$")) {
            return false;
        }

        boolean checkIfAllowedLetter = false;
        boolean checkIfAllowedNumber = false;

        for(int i = 0; i < allowedLetters.length; i++) {
            if(parsedRegister[1].equals(allowedLetters[i])) {
                checkIfAllowedLetter = true;
            }
        }

        for(int i = 0; i < allowedNumbersOrSpecialCharacters.length; i++) {
            if(parsedRegister[2].equals(allowedNumbersOrSpecialCharacters[i])) {
                checkIfAllowedNumber = true;
            }
        }

        return checkIfAllowedLetter && checkIfAllowedNumber;
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
        String[] registers = new String[2];
        String[] parsedInstruction = instruction.split("\\s+");

        // Assuming the MIPS assembly instruction has the format: opcode $reg1, $reg2, ...
        if (parsedInstruction.length >= 4 && parsedInstruction[0].equalsIgnoreCase("add")) {
            registers[0] = parsedInstruction[1].replace(",", ""); // Removing the comma
            registers[1] = parsedInstruction[3];
        } else if (parsedInstruction.length >= 3 && parsedInstruction[0].equalsIgnoreCase("sw")) {
            registers[0] = parsedInstruction[1].replace(",", ""); // Removing the comma
            registers[1] = parsedInstruction[2];
        }

        return registers;
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
