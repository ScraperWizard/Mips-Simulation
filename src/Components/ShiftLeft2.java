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
        output = signExtend.output * 4;
        System.out.println("ShiftLeft2: I returned " +output);
    }
}
