package pl.suseu.gameboy360;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcodes;

public class Main {

    public static void main(String[] args) {
        Opcodes.init();
        GBEmulator emulator = new GBEmulator();

        System.out.println(emulator.getRegisters().toString());
        emulator.tick();

        emulator.tick();
        System.out.println(emulator.getRegisters().toString());

        emulator.tick();
        System.out.println(emulator.getRegisters().toString());

        emulator.tick();
        System.out.println(emulator.getRegisters().toString());

        emulator.tick();
        System.out.println(emulator.getRegisters().toString());

        emulator.tick();
        System.out.println(emulator.getRegisters().toString());

        emulator.tick();
        System.out.println(emulator.getRegisters().toString());
    }

}

