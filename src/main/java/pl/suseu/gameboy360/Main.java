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
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            emulator.tick();
            if (GBEmulator.DEBUG) {
                printRegs();
            }
        }
    }

    public static void printRegs() {
        emulator.getRegisters().print();
    }

    public static class Test {
        public static void main(String[] args) {
            byte b = -120;
            System.out.println((b & 0xFF));
        }
    }

}

