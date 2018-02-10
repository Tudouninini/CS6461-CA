package cpu;

import util.Transform;

public class Registers {
    private int pc;  //Program Counter: address of next instruction to be executed. 12 bits.
    private int cc;  //Condition Code: set when the arithmetic/logical operations are executed. 4 bits.
    private int ir;  //Instruction Register: holds the instruction to be executed. 16 bits.
    private int mar;  //Memory Address Register: holds the address of the word to be fetched frommemory. 16 bits
    private int mbr;  //Memory Buffer Register: holds the word just fetched from or the word to be /last stored into memory. 16 bits
    private int msr;  //Machine Status Register: certain bits record the status of the health of the machine. 16 bits.
    
    /*
     * Machine Fault Register: contains the ID code if a machine fault after it
     * occurs.<br/>
     * 4 bits.<br/>
     * 0 - Illegal Memory Address to Reserved Locations;<br/>
     * 1 - Illegal TRAP code;<br/>
     * 2 - Illegal Operation Code;<br/>
     * 3 - Illegal Memory Address beyond 2048 (memory installed).
     */
    private int mfr;
    
    private int x1;  //Index Register X1. 16 bits.
    private int x2;  //Index Register X2. 16 bits.
    private int x3;  //Index Register X3. 16 bits.
    private int r0;  //General Purpose Register R0. 16 bits.
    private int r1;  //General Purpose Register R1. 16 bits.
    private int r2;  //General Purpose Register R2. 16 bits.
    private int r3;  //General Purpose Register R3. 16 bits.


    //-----initialize all the registers-----//
    public Registers() {
        this.pc = 6;
        this.ir = 0;
        this.cc = 0;
        this.mar = 0;
        this.mbr = 0;
        this.msr = 0;
        this.mfr = 0;
        this.x1 = 0;
        this.x2 = 0;
        this.x3 = 0;
        this.r0 = 0;
        this.r1 = 0;
        this.r2 = 0;
        this.r3 = 0;
    }

    //-----reset all the registers-----//
    public void resetAllRegisters() {
        this.pc = 0;
        this.ir = 0;
        this.cc = 0;
        this.mar = 0;
        this.mbr = 0;
        this.msr = 0;
        this.mfr = 0;
        this.x1 = 0;
        this.x2 = 0;
        this.x3 = 0;
        this.r0 = 0;
        this.r1 = 0;
        this.r2 = 0;
        this.r3 = 0;
    }

    public void pcPromote() {
        this.pc++;
    }


    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        this.cc = cc;
    }

    public int getMar() {
        return mar;
    }

    public void setMar(int mar) {
        this.mar = mar;
    }

    public int getMbr() {
        return mbr;
    }

    public void setMbr(int mbr) {
        this.mbr = mbr;
    }

    public int getMsr() {
        return msr;
    }

    public void setMsr(int msr) {
        this.msr = msr;
    }

    public int getMfr() {
        return mfr;
    }

    public void setMfr(int mfr) {
        this.mfr = mfr;
    }

    //-----Get and set about x1,x2,x3
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getXnByBits(int bits) {
        switch (bits) {
            case 1:
                return this.x1;
            case 2:
                return this.x2;
            case 3:
                return this.x3;
        }
        return 0;
    }

    public void setXnByBits(int bits, int value) {
        switch (bits) {
            case 1:
                this.x1 = value;
            case 2:
                this.x2 = value;
            case 3:
                this.x3 = value;
        }
    }
    //---end of x1,x2,x3


    //Get and set about r0,r1,r2,r3

    public int getR0() {
        return r0;
    }

    public void setR0(int r0) {
        this.r0 = r0;
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public int getR3() {
        return r3;
    }

    public void setR3(int r3) {
        this.r3 = r3;
    }
    //------end of setter getter of r0,r1,r2,r3.

    public void setRnByBits(int n, int value) {
        switch (n) {
            case 0:
                this.r0 = value;
                break;
            case 1:
                this.r1 = value;
                break;
            case 2:
                this.r2 = value;
                break;
            case 3:
                this.r3 = value;
                break;
        }
    }

    public int getRnByBits(int n) {
        switch (n) {
            case 0:
                return this.r0;
            case 1:
                return this.r1;
            case 2:
                return this.r2;
            case 3:
                return this.r3;
        }
        return -1;
    }

    //-----About IR-----//
    public int getIr() {
        return ir;
    }

    public void setIr(int ir) {
        this.ir = ir;
    }

    //int 10---->(16 bits)String "0000000000001010"
    public String getBinaryStringIr() {
        if (this.ir <= 0xffff) return Transform.intToBinaryString(ir);
        return null;
    }

    //------End--About IR-----//

    public int getRegisterBitsByName(String name) {
        if (name.equals("PC"))
            return 12;

        if (name.equals("CC"))
            return 4;
        if (name.equals("IR"))
            return 16;
        if (name.equals("MAR"))
            return 16;
        if (name.equals("MBR"))
            return 16;
        if (name.equals("MSR"))
            return 16;
        if (name.equals("MFR"))
            return 4;
        if (name.equals("X1"))
            return 16;
        if (name.equals("X2"))
            return 16;
        if (name.equals("X3"))
            return 16;
        if (name.equals("R0"))
            return 16;
        if (name.equals("R1"))
            return 16;
        if (name.equals("R2"))
            return 16;
        if (name.equals("R3"))
            return 16;
        return 0;
    }


    public int getRegisterByName(String registerName) {
        switch (registerName) {
            case "PC":
                return this.pc;
            case "CC":
                return this.cc;
            case "IR":
                return this.ir;
            case "MAR":
                return this.mar;
            case "MBR":
                return this.mbr;
            case "MSR":
                return this.msr;
            case "MFR":
                return this.mfr;
            case "X1":
                return this.x1;
            case "X2":
                return this.x2;
            case "X3":
                return this.x3;
            case "R0":
                return this.r0;
            case "R1":
                return this.r1;
            case "R2":
                return this.r2;
            case "R3":
                return this.r3;
        }
        return 0;
    }


}
