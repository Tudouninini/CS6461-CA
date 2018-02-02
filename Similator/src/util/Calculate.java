package util;

import cpu.Registers;
import memory.MemoryManageUnit;

public interface Calculate {
     static int computeEA(int i, int ix, Registers registers, int address, MemoryManageUnit mmu) {
        int ea = 0;
        if (i == 0) {
            if (ix == 0) {
                ea = address;
                return ea;
            } else {
                ea = registers.getXnByBits(ix) + address;
                return ea;
            }
        } else if (i == 1) {
            if (ix == 0) {
                ea = mmu.readMemo(address);
                return ea;
            } else {
                ea = mmu.readMemo(registers.getXnByBits(ix) + address);
                return ea;
            }

        }
        return ea;
    }

}
