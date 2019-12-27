package pl.suseu.gameboy360.emulator.instruction.ins.utils;

import pl.suseu.gameboy360.emulator.GBEmulator;

public class DestinationUtils {
    public static int getFromReg(GBEmulator gb, Register r) {
        switch (r) {
            case A:
                return gb.getRegisters().getA();
            case B:
                return gb.getRegisters().getB();
            case C:
                return gb.getRegisters().getC();
            case D:
                return gb.getRegisters().getD();
            case E:
                return gb.getRegisters().getE();
            case H:
                return gb.getRegisters().getH();
            case L:
                return gb.getRegisters().getL();
            case HL_ADDR:
                return gb.getValueAt(gb.getRegisters().getHL());
        }
        return 0;
    }

    public static void updateReg(GBEmulator gb, Register r, int value) {
        switch (r) {
            case A:
                gb.getRegisters().setA(value);
                break;
            case B:
                gb.getRegisters().setB(value);
                break;
            case C:
                gb.getRegisters().setC(value);
                break;
            case D:
                gb.getRegisters().setD(value);
                break;
            case E:
                gb.getRegisters().setE(value);
                break;
            case H:
                gb.getRegisters().setH(value);
                break;
            case L:
                gb.getRegisters().setL(value);
                break;
            case HL_ADDR:
                gb.getMemoryController().setValue(gb.getRegisters().getHL(), value);
                break;
        }
    }

    public enum Register {
        B(0b000), C(0b001), D(0b010), E(0b011),
        H(0b100), L(0b101), HL_ADDR(0b110), A(0b111);

        final int reg;

        Register(int reg) {
            this.reg = reg;
        }

        public int getReg() {
            return reg;
        }

        public static Register get(int dest) {
            for (Register r : Register.values()) {
                if (r.getReg() == dest) return r;
            }
            return null;
        }
    }
}
