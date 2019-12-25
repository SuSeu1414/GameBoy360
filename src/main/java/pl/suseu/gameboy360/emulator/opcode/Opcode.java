package pl.suseu.gameboy360.emulator.opcode;

import pl.suseu.gameboy360.emulator.GBEmulator;

public interface Opcode {

    void run(GBEmulator emulator);
    void debug(GBEmulator emulator);

}
