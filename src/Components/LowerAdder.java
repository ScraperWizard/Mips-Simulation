package Components;

public class LowerAdder { //Check https://excalidraw.com/#room=7e7f9e982b6558301fd0,8fi-xJMojgOzZKMtvc_cyQ to know what I mean
    int zeroFlag;
    int ALUResult;
    AluControl aluControl;
    RegisterMemory registerMemory;
    Multiplexer ALUSrcMUX;
    int operation;
    int a;
    int b;

    public LowerAdder(){}

    public void update(AluControl aluControl,RegisterMemory registerMemory,Multiplexer ALUSrcMUX){
        this.aluControl= aluControl;
        this.registerMemory=registerMemory;
        this.ALUSrcMUX=ALUSrcMUX;
        this.operation= aluControl.operation;
        this.a= registerMemory.readData1;
        this.b=ALUSrcMUX.AddressDestination;
        if(operation==2)
            add(a,b);
        else if(operation==6)
            sub(a,b);
    }



    private int add(int a,int b){
        return a+b;
    }
    private  int sub(int a,int b){
        return a-b;
    }
    private int and(int a,int b){
        return a&b;
    }
    private int or(int a,int b){return a|b;}
    private int nor(int a,int b){return ~(a|b);}
    private int slt(int a,int b){
        if(a<b)
            return 1;
        else return 0;

    }






}
