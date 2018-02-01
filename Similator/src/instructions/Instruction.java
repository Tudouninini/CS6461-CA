package instructions;

import memory.MemoryManageUnit;
import cpu.Registers;
//The Instruction is a super class for all kinds of instruction.
//
public abstract class Instruction {

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
