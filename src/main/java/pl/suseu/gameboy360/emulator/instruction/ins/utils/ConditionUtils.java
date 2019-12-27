package pl.suseu.gameboy360.emulator.instruction.ins.utils;

import pl.suseu.gameboy360.emulator.GBEmulator;

public class ConditionUtils {

    public static boolean checkCondition(GBEmulator gb, Condition condition) {
        switch (condition){
            case C:
                return gb.getRegisters().getCarryFlag() == 1;
            case NC:
                return gb.getRegisters().getCarryFlag() == 0;
            case Z:
                return gb.getRegisters().getZeroFlag() == 1;
            case NZ:
                return gb.getRegisters().getZeroFlag() == 0;
            default:
                return false;
        }
    }

    public enum Condition {
        NZ(0b00), Z(0b01), NC(0b10), C(0b11);

        private final int cond;

        Condition(int cond) {
            this.cond = cond;
        }

        public int getCond() {
            return cond;
        }

        public static Condition get(int cond) {
            for (Condition c : Condition.values()){
                if (c.getCond() == cond) return c;
            }
            return null;
        }
    }
}
