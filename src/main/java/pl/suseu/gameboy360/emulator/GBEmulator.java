package pl.suseu.gameboy360.emulator;

import pl.suseu.gameboy360.emulator.memory.MemoryController;

public class GBEmulator {

    public static final boolean DEBUG = true;

    private CPU cpu;
    private MemoryController memoryController;

    public GBEmulator(){
        memoryController = new MemoryController(this);
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
