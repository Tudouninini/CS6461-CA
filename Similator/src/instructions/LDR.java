package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;
import util.Transform;
import util.Calculate;

public class LDR extends Instruction {
    private int r;
    private int ix;
    private int i;
    private int address;

    @Override
    public void execute(String instruction, Registers registers, MemoryManageUnit mmu) {
        r = Transform.binaryToDecimal(instruction.substring(6, 8));
        ix = Transform.binaryToDecimal(instruction.substring(8, 10));
        i = Transform.binaryToDecimal(instruction.substring(10, 11));
        address = Transform.binaryToDecimal(instruction.substring(11, 16));
        int ea = Calculate.computeEA(i, ix, registers, address, mmu);
        registers.setMar(ea);
        registers.setMbr(mmu.readMemo(ea));
        registers.setRnByBits(r, registers.getMbr());
        registers.pcPromote();
    }

    @Override
    public String getExecuteInfo() {
        //LDR r, x, address[,I]
        return "LDR " + r + ", " + ix + ", " + address + "[," + i + "]";
    }
}
