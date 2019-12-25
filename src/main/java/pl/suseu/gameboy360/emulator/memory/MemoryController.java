package pl.suseu.gameboy360.emulator.memory;

import pl.suseu.gameboy360.emulator.GBEmulator;

import java.util.ArrayList;
import java.util.List;

public class MemoryController {

    private GBEmulator emulator;
    private int[] memory = new int[0xFFFF+1]; //not sure if +1 is necessary.

    public MemoryController(GBEmulator emulator){
        this.emulator = emulator;

        //copy rom
        //this will be moved somewhere else probably
        for (int i = 0; i < BootRom.ROM.length; i++) {
            memory[i] = BootRom.ROM[i]; //TODO: copy func
        }
    }

}
