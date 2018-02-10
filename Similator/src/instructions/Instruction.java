package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;

//The Instruction is a super class for all kinds of instruction.
//
public abstract class Instruction {
    public abstract int executeSingleStep(String instruction, Registers registers, MemoryManageUnit mmu, int control);


    /*execute(): Execute instruction
     */

    //* @param instruction: the instruction information which is 16 bits.

    //*@param register: register need to use to execute instructions

    //*@param mmu is memory control unit to

    public abstract void execute(String instruction, Registers registers, MemoryManageUnit mmu);

    /*
    Return a feedback information after every execution
     */
    public abstract String getExecuteInfo();

}
