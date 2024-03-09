package Components;

public class AndGate {

    public int controlUnitForPCSrc;
    int Branch2;
    int zeroFlag2;

    public AndGate(){    }

    public void update (ControlUnit controlUnit, LowerAdder lowerAdder){
        this.Branch2 = controlUnit.Branch;
        this.zeroFlag2 = lowerAdder.zeroFlag;
        controlUnitForPCSrcDecider();
    }

    private void controlUnitForPCSrcDecider(){
        if(Branch2*zeroFlag2 == 0){
            controlUnitForPCSrc = 0;
        }
        else if (Branch2*zeroFlag2 == 1){
            controlUnitForPCSrc = 1;
        }
    }


}
