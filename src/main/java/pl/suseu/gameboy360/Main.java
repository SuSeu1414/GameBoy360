package pl.suseu.gameboy360;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcodes;

public class Main {

    private static GBEmulator emulator;

    public static void main(String[] args) {
        Opcodes.init();
        emulator = new GBEmulator();

        printRegs();
        while (true) {
            emulator.tick();
            printRegs();
        }
    }

    public static void printRegs() {
        emulator.getRegisters().print();
    }

}

