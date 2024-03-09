package Components;
import Compiler.MipsInstruction;
import Compiler.ITypeMipsInstruction;
public class SignExtend {
    public int input;
    public int output;

    public SignExtend(){

    }

    public void update(MipsInstruction mipsInstruction){
        ITypeMipsInstruction instruction =(ITypeMipsInstruction) mipsInstruction;
        this.input=instruction.getOffset();
        this.output=this.input;
    }
}
