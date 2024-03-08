package Components;

public class AndGate {
    int controlUnitForPCSrc;
    int Branch2;
    int zeroFlag2;

    AndGate(ControlUnit controlUnit, LowerAdder lowerAdder){
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