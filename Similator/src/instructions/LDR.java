package instructions;

import cpu.Registers;
import memory.MemoryManageUnit;
import util.HelpTool;

import static util.Transform.binaryToDecimal;

public class LDR extends Instruction {

    private int r;
    private int ix;
    private int i;
    private int address;

    @Override
    public void execute(String instruction, Registers registers, MemoryManageUnit mmu) {
        r = binaryToDecimal(instruction.substring(6, 8));
        ix = binaryToDecimal(instruction.substring(8, 10));
        i = binaryToDecimal(instruction.substring(10, 11));
        address = binaryToDecimal(instruction.substring(11, 16));

        int ea = HelpTool.computeEA(i, ix, registers, address, mmu);
        registers.setMar(ea);
        registers.setMbr(mmu.readMemo(ea));
        registers.setRnByBits(r, registers.getMbr());
        registers.pcPromote();

    }

    public int executeSingleStep(String instruction, Registers registers, MemoryManageUnit mmu, int control) {
        switch (control) {
            case 0: {
                ix = binaryToDecimal(instruction.substring(8, 10));
                r = binaryToDecimal(instruction.substring(6, 8));
                i = binaryToDecimal(instruction.substring(10, 11));
                address = binaryToDecimal(instruction.substring(11, 16));
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
                registers.setRnByBits(r, registers.getMbr());
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


    @Override
    public String getExecuteInfo() {
        //LDR r, x, address[,I]
        return "LDR " + r + ", " + ix + ", " + address + "[," + i + "]";
    }

}
