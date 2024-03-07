package Components;

public class UpperAdd {
    int ALUResult;
    ShiftLeft2 shiftLeft2;
    Adder adder;

    UpperAdd(ShiftLeft2 givenShiftLeft2, Adder givenAdder){
        this.shiftLeft2 = givenShiftLeft2;
        this.adder = givenAdder;
        UpperAddOperation();
    }

    void UpperAddOperation(){
        System.out.println("UpperAdder: I retuned shiftefLeftValue + incrementedAddress");
        ALUResult = shiftLeft2.shiftLeftBy2() + adder.incrementedAddress();
    }
}
