package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;
import util.HelpTool;

import static util.Transform.binaryToDecimal;

public class STR extends Instruction {

    private int r;
    private int ix;
    private int i;
    private int address;

    @Override
    public void execute(String instruction, Registers registers, MemoryManageUnit mmu) {
        // TODO Auto-generated method stub
        r = binaryToDecimal(instruction.substring(6, 8));
        ix = binaryToDecimal(instruction.substring(8, 10));
        i = binaryToDecimal(instruction.substring(10, 11));
        address = binaryToDecimal(instruction.substring(11, 16));
        int ea = HelpTool.computeEA(i, ix, registers, address, mmu);
        registers.setMar(ea);
        registers.setMbr(registers.getRnByBits(r));
        mmu.writeMemo(registers.getMar(), registers.getMbr());
        registers.pcPromote();
    }

    @Override
    public String getExecuteInfo() {
        // TODO Auto-generated method stub
        return "STR " + r + ", " + ix + ", " + address + "[," + i + "]";
    }

    @Override
    @SuppressWarnings("Duplicates")
    public int executeSingleStep(String instruction, Registers registers, MemoryManageUnit mmu, int control) {
        // TODO Auto-generated method stub
        switch (control) {
            case 0: {
                r = binaryToDecimal(instruction.substring(6, 8));
                ix = binaryToDecimal(instruction.substring(8, 10));
                i = binaryToDecimal(instruction.substring(10, 11));
                address = binaryToDecimal(instruction.substring(11, 16));
                int ea = HelpTool.computeEA(i, ix, registers, address, mmu);
                registers.setMar(ea);
                control++;

                return control;
            }
            case 1: {
                r = binaryToDecimal(instruction.substring(6, 8));
                registers.setMbr(registers.getRnByBits(r));
                control++;
                return control;
            }
            case 2: {
                mmu.writeMemo(registers.getMar(), registers.getMbr());
                control++;
                return control;
            }
            case 3: {
                registers.pcPromote();
                control = 0;
                return control;
            }
        }
        return control;
    }
}
