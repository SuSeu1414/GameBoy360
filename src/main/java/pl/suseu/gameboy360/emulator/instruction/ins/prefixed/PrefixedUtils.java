package pl.suseu.gameboy360.emulator.instruction.ins.prefixed;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.instruction.ins.utils.RegisterUtils;

public class PrefixedUtils {

    public static void testBit(GBEmulator gb, RegisterUtils.Register r, int b) {
        int value = RegisterUtils.getFromReg(gb, r);
        int bit = (value >>> b) & 1;
        gb.getRegisters().setOperationFlag(0);
        gb.getRegisters().setHalfCarryFlag(1);
        if (bit == 0)
            gb.getRegisters().setZeroFlag(1);
        else
            gb.getRegisters().setZeroFlag(0);
    }

    public static void setBit(GBEmulator gb, RegisterUtils.Register r, int b) {
        int value = RegisterUtils.getFromReg(gb, r);
        value |= (1 << b);
        RegisterUtils.updateReg(gb, r, value);
    }

    public static void resetBit(GBEmulator gb, RegisterUtils.Register r, int b) {
        int value = RegisterUtils.getFromReg(gb, r);
        value &= ~(1 << b);
        RegisterUtils.updateReg(gb, r, value);
    }


}
