package Compiler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Compiler {
    private final List<Command> commands = new ArrayList<Command>();

    public Compiler() {
        this.loadCommands();
    }

    public String[] getValidKeyWordInstructions() {
        return new String[]{"add", "addi", "lw", "sw", "sll", "and", "andi", "or", "ori", "nor", "beq", "j", "jal", "jr", "slt"};
    }

    public HashMap<String, Integer> getOpCodeMap() {
        HashMap<String, Integer> opCodesMap = new HashMap<>();
        for (Command command : commands) {
            opCodesMap.put(command.getName(), command.getOp());
        }
        return opCodesMap;
    }

    public boolean isCommandExist(String name) {
        return this.getCommandByName(name) != null;
    }

    public int getFunctionCodeByCommandName(String name) {
        return this.getCommandByName(name).getFunc();
    }

    public int getOpCodeByCommandName(String name) {
        return this.getCommandByName(name).getOp();
    }

    public String getTypeByCommandName(String name) {
        return this.getCommandByName(name).getType();
    }

    private Command getCommandByName(String name) {
        Command command = null;

        for(int i = 0; i < commands.size(); i++) {
            if(commands.get(i).getName().equals(name)) {
                command = commands.get(i);
            }
        }

        return command;
    }

    private void loadCommands() {
        try {
            JSONParser parser = new JSONParser();
            JSONArray instructionsArray = (JSONArray) parser.parse(new FileReader("src/Compiler/commands.json"));

            for (Object instructionObj : instructionsArray) {
                JSONObject instruction = (JSONObject) instructionObj;
                String name = (String) instruction.get("name");
                String type = (String) instruction.get("type");
                long opCode = (long) instruction.get("op");
                long funcCode = (long) instruction.get("func");

                commands.add(new Command(name, type, (int) opCode, (int) funcCode));
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
