package pl.suseu.gameboy360.emulator;

import pl.suseu.gameboy360.emulator.memory.MemoryController;

public class GBEmulator {

    private CPU cpu;
    private MemoryController memoryController;

    public GBEmulator(){
        memoryController = new MemoryController();
        cpu = new CPU();
    }
}
