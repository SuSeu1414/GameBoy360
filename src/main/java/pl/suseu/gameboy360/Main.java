package pl.suseu.gameboy360;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcodes;

public class Main {

    private static GBEmulator emulator;

    public static void main(String[] args) {
        Opcodes.init();
        emulator = new GBEmulator();

        printRegs();
        emulator.getRegisters().setCarryFlag(1);
        printRegs();
        emulator.getRegisters().setCarryFlag(0);
        printRegs();

        while (true) {
            emulator.tick();
            printRegs();
        }
    }

    public static void printRegs() {
        System.out.println(emulator.getRegisters().toString());
    }

}

