package Components;

public class LowerAdder { //Check https://excalidraw.com/#room=7e7f9e982b6558301fd0,8fi-xJMojgOzZKMtvc_cyQ to know what I mean
    public int zeroFlag;
    int ALUResult;
    AluControl aluControl;
    RegisterMemory registerMemory;
    Multiplexer ALUSrcMUX;
    int operation;
    int a;
    int b;
    int output;

    public LowerAdder(){}
    public void update(AluControl aluControl, RegisterMemory registerMemory, Multiplexer ALUSrcMUX) {
        this.aluControl = aluControl;
        this.registerMemory = registerMemory;
        this.ALUSrcMUX = ALUSrcMUX;
        this.operation = aluControl.operation;
        this.a = registerMemory.readData1;
        this.b = ALUSrcMUX.AddressDestination;

        zeroFlag = a == b ? 1: 0;
        if(operation==2)
            add(a,b);
        else if(operation==6)
            sub(a,b);
        else if(operation==0)
            and(a,b);
        else if(operation==1)
            or(a,b);
        else if(operation==12)
            nor(a,b);
        else if(operation==7)
            slt(a,b);
        else if(operation==8)
            sll(a,b);
        else if(operation==3)
            mult(a,b);
    }

    private void add(int a,int b) {
        output = a + b;
        System.out.println(a + " + " + b + " = " + output);
    }
    private  void mult(int a, int b){
        output= a*b;
        System.out.println(a+ " x "+ b + " = "+ output);
    }
    private  void sub(int a,int b){
        output= a-b;
        System.out.println(a + " - " + b + " = " + output);
    }
    private void and(int a,int b){
        output= a&b;
        System.out.println(a + " AND " + b + " = " + output);
    }
    private void or(int a,int b){
        output= a|b;
        System.out.println(a + " OR " + b + " = " + output);
    }
    private void nor(int a,int b){
        output= ~(a|b);
        System.out.println(a + " NOR " + b + " = " + output);
    }
    private void slt(int a,int b){
        if(a<b){
            output= 1;
            System.out.println(a +" is less than "+b);
        }
        else {
            output= 0;
            System.out.println(a +" is not less than "+b);
        }
    }
    private void sll(int a, int b){
        output= (int) (a*Math.pow(2,b));
        System.out.println(a + " x 2^" + b + " = " + output);
    }
}
