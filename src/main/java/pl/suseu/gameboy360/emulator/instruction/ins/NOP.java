package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class NOP extends Opcode {
    public NOP() {
        super("NOP", (emulator, instruction) -> {});
    }
}
