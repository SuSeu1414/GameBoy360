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

    private static void updateValue(GBEmulator gb, RegisterUtils.Register r, int val) {
        if (val == 0)
            gb.getRegisters().setZeroFlag(1);
        else
            gb.getRegisters().setZeroFlag(0);
        gb.getRegisters().setOperationFlag(0);
        gb.getRegisters().setHalfCarryFlag(0);
        RegisterUtils.updateReg(gb, r, val);
    }

    public static void rotateLeft(GBEmulator gb, RegisterUtils.Register r) {
        int val = RegisterUtils.getFromReg(gb, r);
        int carry = gb.getRegisters().getCarryFlag();
        gb.getRegisters().setCarryFlag((val & 0b10000000) >> 7);
        rotateLeftAndUpdate(gb, r, val, carry);
    }

    public static void rotateLeftCarry(GBEmulator gb, RegisterUtils.Register r) {
        int val = RegisterUtils.getFromReg(gb, r);
        int bit7 = (val & 0b10000000) >>> 7;
        gb.getRegisters().setCarryFlag(bit7);
        rotateLeftAndUpdate(gb, r, val, bit7);
    }

    private static void rotateLeftAndUpdate(GBEmulator gb, RegisterUtils.Register r, int val, int bit7) {
        val <<= 1;
        val &= 0xFF;
        val |= bit7;
        updateValue(gb, r, val);
    }

    public static void rotateRight(GBEmulator gb, RegisterUtils.Register r) {
        int val = RegisterUtils.getFromReg(gb, r);
        int carry = gb.getRegisters().getCarryFlag();
        carry <<= 7;
        gb.getRegisters().setCarryFlag(val & 1);
        val >>>= 1;
        val |= carry;
        val &= 0xFF;
        updateValue(gb, r, val);
    }

    public static void rotateRightCarry(GBEmulator gb, RegisterUtils.Register r) {
        int val = RegisterUtils.getFromReg(gb, r);
        int bit0 = val & 1;
        val >>>= 1;
        val |= (bit0 << 7);
        val &= 0xFF;
        updateValue(gb, r, val);
    }

    public static void shiftLeft(GBEmulator gb, RegisterUtils.Register r) {
        int val = RegisterUtils.getFromReg(gb, r);
        gb.getRegisters().setCarryFlag((val & 0b10000000) >>> 7);
        val <<= 1;
        val &= 0xFF;
        updateValue(gb, r, val);
    }

    public static void shiftRightA(GBEmulator gb, RegisterUtils.Register r) {
        int val = RegisterUtils.getFromReg(gb, r);
        gb.getRegisters().setCarryFlag(val & 1);
        int bit7 = val & 0b10000000;
        val &= 0b01111111;
        val >>>= 1;
        val |= bit7;
        updateValue(gb, r, val);
    }

    public static void shiftRightL(GBEmulator gb, RegisterUtils.Register r) {
        int val = RegisterUtils.getFromReg(gb, r);
        gb.getRegisters().setCarryFlag(val & 1);
        val >>>= 1;
        updateValue(gb, r, val);
    }

}
