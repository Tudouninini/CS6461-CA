package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;

public class HLT extends Instruction {


    @Override
    public void execute(String instruction, Registers registers, MemoryManageUnit mmu) {
        if (instruction.substring(0, 6).equals("000000")) System.out.println("HALT");
        //todo- end process
    }

    @Override
    public String getExecuteInfo() {
        return "HALT";
    }
}
