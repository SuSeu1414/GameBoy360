package pl.suseu.gameboy360.emulator.instruction.ins.prefixed;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.instruction.ins.utils.DestinationUtils;

public class PrefixedUtils {

    public static void testBit(GBEmulator gb, DestinationUtils.Register r, int b) {
        int value = DestinationUtils.getFromReg(gb, r);
        int bit = (value >>> b) & 1;
        gb.getRegisters().setOperationFlag(0);
        gb.getRegisters().setHalfCarryFlag(1);
        if (bit == 0)
            gb.getRegisters().setZeroFlag(1);
        else
            gb.getRegisters().setZeroFlag(0);
    }

    public static void setBit(GBEmulator gb, DestinationUtils.Register r, int b) {
        int value = DestinationUtils.getFromReg(gb, r);
        value |= (1 << b);
        DestinationUtils.updateReg(gb, r, value);
    }

    public static void resetBit(GBEmulator gb, DestinationUtils.Register r, int b) {
        int value = DestinationUtils.getFromReg(gb, r);
        value &= ~(1 << b);
        DestinationUtils.updateReg(gb, r, value);
    }


}
