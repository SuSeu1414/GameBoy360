package pl.suseu.gameboy360.emulator.memory;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

import java.util.ArrayList;
import java.util.List;

public class MemoryController {

    private GBEmulator emulator;
    private int[] memory = new int[0xFFFF+1]; //not sure if +1 is necessary.

    public MemoryController(GBEmulator emulator){
        this.emulator = emulator;

        //copy boot rom
        //this will be moved somewhere else probably
        for (int i = 0; i < BootRom.ROM.length; i++) {
            memory[i] = BootRom.ROM[i]; //TODO: copy func
        }
    }

    public void setValue(int address, int value) {
        memory[address] = value;
    }

    public int getValue(int address){
        return memory[address];
    }

}
