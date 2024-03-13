package Components;

public class AndGate {
    public int controlUnitForPCSrc;
    private ControlUnit controlUnit;
    private LowerAdder lowerAdder;

    public AndGate(ControlUnit controlUnit, LowerAdder lowerAdder){
        this.controlUnit = controlUnit;
        this.lowerAdder = lowerAdder;
    }

    public void update () {
        controlUnitForPCSrcDecider();
    }

    private void controlUnitForPCSrcDecider(){
        int Branch2 = controlUnit.Branch;
        int zeroFlag2 = lowerAdder.zeroFlag;

        controlUnitForPCSrc=Branch2*zeroFlag2;

        System.out.println("And Gate:");
        System.out.println(" Output is: "+controlUnitForPCSrc);
    }

}
