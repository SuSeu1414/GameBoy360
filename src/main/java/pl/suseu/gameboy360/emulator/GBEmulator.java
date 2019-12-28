package pl.suseu.gameboy360.emulator;

import pl.suseu.gameboy360.emulator.memory.Memory;
import pl.suseu.gameboy360.emulator.memory.MemoryController;
import pl.suseu.gameboy360.util.Utils;

public class GBEmulator {

    public static boolean DEBUG = true;

    private CPU cpu;
    private MemoryController memoryController;

    public GBEmulator(){
        memoryController = new MemoryController(this);
        memoryController.loadCartridge("roms/TETRIS.gb");
        cpu = new CPU(this);
    }

    public void tick(){
        cpu.tick();
    }

    public int getPc(){
        return getRegisters().getPc();
    }

    public void incrementPc(){
        getRegisters().incPc();
    }

    public int incrementPcAndReturn(){
        incrementPc();
        return getRegisters().getPc();
    }

    public int getValueAt(int address) {
        return memoryController.getValue(address);
    }

    public int getValueAtPc(){
        return getValueAt(cpu.getRegisters().getPc());
    }

    public int incrementPcAndGetValueAtPc(){
        incrementPc();
        return getValueAtPc();
    }

    //---

    public static void debug(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

    public Registers getRegisters(){
        return cpu.getRegisters();
    }

    public CPU getCpu() {
        return cpu;
    }

    public MemoryController getMemoryController() {
        return memoryController;
    }
}
