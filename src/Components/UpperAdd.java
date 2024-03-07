package Components;

public class UpperAdd {
    int ALUResult;
    ShiftLeft2 shiftLeft2 = new ShiftLeft2();
    Adder adder = new Adder();

    int UpperAddOperation(){
        System.out.println("UpperAdder: I retuned shiftefLeftValue + incrementedAddress");
        ALUResult = shiftLeft2.shiftLeftBy2() + adder.incrementedAddress();
        return ALUResult;
    }
}
