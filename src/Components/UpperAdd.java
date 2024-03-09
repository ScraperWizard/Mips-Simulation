package Components;

public class UpperAdd { //Check https://excalidraw.com/#room=7e7f9e982b6558301fd0,8fi-xJMojgOzZKMtvc_cyQ to know what I mean
    int ALUResult; // Output
    ShiftLeft2 shiftLeft2; // input
    Adder adder; // input

    public UpperAdd(){    }

    public void update (ShiftLeft2 givenShiftLeft2, Adder givenAdder){
        this.shiftLeft2 = givenShiftLeft2;
        this.adder = givenAdder;
        UpperAddOperation();
    }

    void UpperAddOperation(){

        ALUResult = shiftLeft2.output + adder.nextAddress;
        System.out.println("UpperAdder: "+ shiftLeft2.output +" + " + adder.nextAddress+" = " +ALUResult);
    }
}
