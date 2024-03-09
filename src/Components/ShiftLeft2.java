package Components;

public class ShiftLeft2 {
    Sign bit32FromSignExtend;
    public ShiftLeft2() {  }

    public void update(int given32bit){
        bit32FromSignExtend = given32bit;
    }
    public int shiftLeftBy2(){
        System.out.println("ShiftLeft2: I returned 32 bits from Sign Extend*4");
        return bit32FromSignExtend*4;
    }
}
