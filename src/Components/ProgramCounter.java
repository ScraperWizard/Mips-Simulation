package Components;

import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;

public class ProgramCounter {
    Register address;
    RegisterProvider registerProvider;
    int counter;
    ProgramCounter(Register address, RegisterProvider registerProvider) {
        this.address = address;
        this.registerProvider = registerProvider;
    }

    public ProgramCounter (){

    }

    public int getCounter(){
        return counter;

    }

    public void increment(){
        return;
    }

    public void setCounter(int a){
        counter=a;
        return;
    }
}
