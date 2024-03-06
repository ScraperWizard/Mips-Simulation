package Compiler;

import java.util.HashMap;

public class Compiler {
    public Compiler() {

    }

    public String[] getValidKeyWordInstructions() {
        return new String[]{"add", "addi", "lw", "sw", "sll", "and", "andi", "or", "ori", "nor", "beq", "j", "jal", "jr", "slt"};
    }

    public HashMap<String, Integer> getOpCodeMap() {
        HashMap<String, Integer> OpCodesMap = new HashMap<String, Integer>();

        return OpCodesMap;
    }
}
