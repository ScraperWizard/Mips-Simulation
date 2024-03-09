package Components;

public class ShiftLeft2 {
    SignExtend signExtend;
    int output;
    public ShiftLeft2() {  }

    public void update(SignExtend signExtend){
        this.signExtend = signExtend;
        shiftLeftBy2();
    }
    public void shiftLeftBy2(){
        System.out.println("ShiftLeft2: I returned 32 bits from Sign Extend*4");
        output = output*4;
    }
}
