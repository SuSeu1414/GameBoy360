package pl.suseu.gameboy360.emulator.memory;

public class Cartridge {

    private int type; // 0 - rom only. I'll make it an enum someday
    private int state = 0; //not used now, but I guess it'll be useful in the future
    private int[] rom;
    private int[] ram;

    public Cartridge(int type, int[] rom) {
        this.type = type;
        this.rom = rom;
    }

    public int getValueFromRom(int address) {
        if (type == 0) {
            return rom[address];
        }
        return 0;
    }

}
