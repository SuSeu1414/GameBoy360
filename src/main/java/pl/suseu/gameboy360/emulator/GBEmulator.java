package pl.suseu.gameboy360.emulator;

public class GBEmulator {

    private CPU cpu;
    private MemoryController memoryController;

    public GBEmulator(){
        memoryController = new MemoryController();
        cpu = new CPU();
    }
}
