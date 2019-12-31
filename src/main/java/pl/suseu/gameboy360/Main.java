package pl.suseu.gameboy360;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.gpu.GPUApplet;
import pl.suseu.gameboy360.emulator.opcode.Opcodes;
import processing.core.PApplet;

public class Main {

    private static GBEmulator gb;

    public static void main(String[] args) {
        Opcodes.init();
        gb = new GBEmulator();

        while (!gb.isReady()) { }


        printRegs();
        new Thread(() -> {
            while (true) {
                gb.tick();
                if (GBEmulator.DEBUG) {
                    printRegs();
                }
            }
        }).start();
    }

    public static void printRegs() {
        gb.getRegisters().print();
    }

    public static class Test {
        public static void main(String[] args) {

        }
    }

}

