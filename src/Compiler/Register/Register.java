package Compiler.Register;

public class Register {
    private int RegisterId;
    private String RegisterHumanName;
    private int value;
    public Register(String RegisterHumanName, int RegisterId) {
        this.RegisterId = RegisterId;
        this.RegisterHumanName = RegisterHumanName;
        this.value = 0;
    }

    public int getRegisterId() {
        return RegisterId;
    }

    public String getRegisterHumanName() {
        return RegisterHumanName;
    }

    public int getValue() {
        return value;
    }
}
