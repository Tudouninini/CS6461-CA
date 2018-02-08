package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;
import util.HelpTool;
import util.Transform;

public class LDR extends Instruction {
    public static int quanju = 0;
    private int r;
    private int ix;
    private int i;
    private int address;

    @Override
    public void execute(String instruction, Registers registers, MemoryManageUnit mmu) {
        switch (quanju) {

            case 0:
                ix = Transform.binaryToDecimal(instruction.substring(8, 10));
                i = Transform.binaryToDecimal(instruction.substring(10, 11));
                address = Transform.binaryToDecimal(instruction.substring(11, 16));
                int ea = HelpTool.computeEA(i, ix, registers, address, mmu);
                registers.setMar(ea);
                quanju++;
            case 1:
                registers.setMbr(mmu.readMemo(registers.getMar()));
                quanju++;
            case 2:
                registers.setRnByBits(r, registers.getMbr());
                quanju++;
            case 3:
                registers.pcPromote();
                quanju = 0;

        }

    }

    @Override
    public String getExecuteInfo() {
        //LDR r, x, address[,I]
        return "LDR " + r + ", " + ix + ", " + address + "[," + i + "]";
    }
}
