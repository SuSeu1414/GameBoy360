package pl.suseu.gameboy360.emulator.memory;

import static pl.suseu.gameboy360.emulator.memory.MemoryMap.*;

import pl.suseu.gameboy360.emulator.GBEmulator;

public class MemoryController {

    private GBEmulator emulator;
    private boolean bootRomEnabled = true;
    private int[] memory = new int[0xFFFF+1]; //not sure if +1 is necessary.
    private Cartridge cartridge;

    public MemoryController(GBEmulator emulator){
        this.emulator = emulator;

//        //copy boot rom
//        //this will be moved somewhere else probably
//        for (int i = 0; i < BootRom.ROM.length; i++) {
//            memory[i] = BootRom.ROM[i]; //TODO: copy func
//        }
    }

    public void loadCartridge(String name) {
        try {
            int[] rom = MemoryUtils.loadBinary(name);
            cartridge = new Cartridge(0, rom); // Type 0 for now
        } catch (Exception e) {
            System.err.println("Error loading cartridge!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void setValue(int address, int value) {
        GBEmulator.debug("memory[0x" + Integer.toHexString(address) + "]=0x" + Integer.toHexString(value));
        memory[address] = value;
    }

    public int getValue(int address){
        if (address <= BOOT_END && bootRomEnabled){
            return BootRom.ROM[address];
        }

        if (address <= CARTRIDGE_ROM_END) {
            return cartridge.getValueFromRom(address);
        }

        return memory[address];
    }

}
