package pl.suseu.gameboy360.emulator.instruction;

import pl.suseu.gameboy360.emulator.CPU;
import pl.suseu.gameboy360.emulator.GBEmulator;

public interface Step {
    void execute(GBEmulator emulator, Instruction instruction);
}
