package Components;

import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;

public class ProgramCounter {
    Register address;
    RegisterProvider registerProvider;
    ProgramCounter(Register address, RegisterProvider registerProvider){
        this.address = address;
        this.registerProvider = registerProvider;
    }

    public ProgramCounter (){

    }

    public int getCounter(){
        return 0;

    }

    public void increment(){
        return;
    }

    public void setCounter(int a){

        return;
    }
}
