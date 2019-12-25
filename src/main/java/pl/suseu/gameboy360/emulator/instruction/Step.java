package pl.suseu.gameboy360.emulator.instruction;

import pl.suseu.gameboy360.emulator.CPU;

public interface Step {

    Instruction ins = null;
    void execute(CPU cpu, Instruction instruction);

}
