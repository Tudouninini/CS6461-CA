package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;

public class HLT extends Instruction {


    @Override
    public void execute(String instruction, Registers registers, MemoryManageUnit mmu) {

    }

    @Override
    public int executeSingleStep(String instruction, Registers registers, MemoryManageUnit mmu, int control) {
        return 0;
    }

    @Override
    public String getExecuteInfo() {
        return "HALT";
    }

}
