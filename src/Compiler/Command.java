package Compiler;

public class Command {
    private String name;
    private String type;
    private int op; // Change the type to int
    private int func;

    public Command(String name, String type, int op, int func) {
        this.name = name;
        this.type = type;
        this.op = op;
        this.func = func;
    }

    // Getter methods for the fields
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getOp() {
        return op;
    }

    public int getFunc() {
        return func;
    }
}
