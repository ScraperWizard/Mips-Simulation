import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Scanner;

import Compiler.Addresses.AddressProvider;
import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;
import Components.*;
import Compiler.Compiler;
import Compiler.InstructionParser;
import Compiler.MipsInstruction;
import Compiler.RTypeMipsInstruction;
import Compiler.ITypeMipsInstruction;
import GUI.GalaxyCompilerV2;

import javax.swing.*;

public class Test2 {
    public static void main(String[] args){



        String[] commands=null;
        AddressProvider addressProvider = new AddressProvider();
        RegisterProvider registerProvider = new RegisterProvider(addressProvider);
        GalaxyCompilerV2 GUI = new GalaxyCompilerV2(addressProvider, registerProvider);
        GUI.setVisible(true);

        GUI.executeButton.addActionListener(e -> executeClick(GUI,addressProvider,registerProvider));






    }

    public static void executeClick(GalaxyCompilerV2 GUI,AddressProvider addressProvider, RegisterProvider registerProvider){
        String[] commands;
        commands=GUI.executeCode();
        System.out.println(Arrays.toString(commands));
        Scanner sova= new Scanner(System.in);

        Compiler mmipsCompiler = new Compiler();
        registerProvider.getRegisterByHumanName("$s1").setValue(10);
        registerProvider.getRegisterByHumanName("$s2").setValue(5);
        registerProvider.getRegisterByHumanName("$s3").setValue(9);
        registerProvider.getRegisterByHumanName("$s4").setValue(0);

        Adder adder=new Adder();
        AluControl aluControl= new AluControl();
        AndGate andGate = new AndGate();
        ControlUnit controlUnit = new ControlUnit();
        LowerAdder lowerAdder = new LowerAdder();
        RegisterMemory registerMemory = new RegisterMemory();
        ProgramCounter programCounter= new ProgramCounter();
        ShiftLeft2 shiftLeft2 = new ShiftLeft2();
        SignExtend signExtend= new SignExtend();
        UpperAdd upperAdd = new UpperAdd();
        DataMemory dataMemory= new DataMemory();

        programCounter.setCounter(0);



        for(int i=0;i<commands.length;){

            InstructionParser instructionParser = new InstructionParser(commands[i],mmipsCompiler,registerProvider);
            MipsInstruction mipsInstruction = instructionParser.parse();
            MipsInstruction instruction;

            if(mipsInstruction instanceof RTypeMipsInstruction){
                instruction  =(RTypeMipsInstruction) mipsInstruction;
                System.out.println("I am R Type");
            }
            else if(mipsInstruction instanceof ITypeMipsInstruction){
                instruction = (ITypeMipsInstruction) mipsInstruction;
                System.out.println("I am I Type");

            }
            else {
                instruction = instructionParser.parse();
                System.out.println("Invalid function type");
            }

            System.out.println("op code: "+instruction.getOpCode());
            System.out.println("Source Reg: "+ instruction.getSourceAddress().getValue());
            System.out.println("Target Reg: "+ ((RTypeMipsInstruction)instruction).getTargetAddress().getValue());
            //stage 1

            controlUnit.update(instruction);
            adder.update(programCounter);
            //stage 2
            if(instruction instanceof RTypeMipsInstruction){
                registerMemory.readFromRegisters((RTypeMipsInstruction) instruction);
                System.out.println(((RTypeMipsInstruction) instruction).getDestinationAddress().getValue());
            }
            else if(instruction instanceof ITypeMipsInstruction){
                registerMemory.readFromRegisters((ITypeMipsInstruction) instruction);

            }

            signExtend.update(instruction);
            //stage 3
            Multiplexer ALUSrc = new Multiplexer("ALUSrc",controlUnit,registerMemory,signExtend);
            aluControl.update(controlUnit,mipsInstruction);
            shiftLeft2.update(signExtend);
            //stage 4
            lowerAdder.update(aluControl,registerMemory,ALUSrc);
            upperAdd.update(shiftLeft2,adder);
            andGate.update(controlUnit,lowerAdder);
            //stage 5
            dataMemory.update(controlUnit,lowerAdder,registerMemory,addressProvider);
            Multiplexer MemToReg = new Multiplexer("MemToReg",controlUnit,lowerAdder,dataMemory);
            Multiplexer PCSrc = new Multiplexer("PCsrc",andGate,upperAdd,adder);
            registerMemory.update(MemToReg,controlUnit);
            registerMemory.writeToRegister();
            programCounter.setCounter(PCSrc.AddressDestination);

            if(instruction instanceof RTypeMipsInstruction){
                System.out.println("Final Output is: "+((RTypeMipsInstruction) instruction).getDestinationAddress().getValue());
            }
            else if(instruction instanceof ITypeMipsInstruction){
                System.out.println(((ITypeMipsInstruction) instruction).getTargetAddress().getValue());

            }




            i=programCounter.getCounter()/4;

            System.out.println("Type anything to advance to next instruction");
            sova.next();
        }

    //lw $s1 20($s2)--> rt $s1 rd $s2 16bit 20
    //sw $s1 20($s2)

    }
}


