package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;
import util.HelpTool;

import static util.Transform.binaryToDecimal;

public class LDX extends Instruction {
    public static int globalVal = 0;

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
        registers.setMbr(mmu.readMemo(ea));
        registers.setXnByBits(r, registers.getMbr());
        registers.pcPromote();
    }

    @Override
    public String getExecuteInfo() {
        // TODO Auto-generated method stub
        return "LDX " + r + ", " + ix + ", " + address + "[," + i + "]";
    }

    @Override
    public int executeSingleStep(String instruction, Registers registers, MemoryManageUnit mmu, int control) {
        // TODO Auto-generated method stub

        switch (control) {
            case 0: {
                ix = binaryToDecimal(instruction.substring(8, 10));
                address = binaryToDecimal(instruction.substring(11, 16));
                r = binaryToDecimal(instruction.substring(6, 8));
                i = binaryToDecimal(instruction.substring(10, 11));

                int ea = HelpTool.computeEA(i, ix, registers, address, mmu);
                registers.setMar(ea);
                control++;
                return control;
            }
            case 1: {
                registers.setMbr(mmu.readMemo(registers.getMar()));
                control++;
                return control;
            }
            case 2: {
                r = binaryToDecimal(instruction.substring(6, 8));
                registers.setXnByBits(r, registers.getMbr());
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
