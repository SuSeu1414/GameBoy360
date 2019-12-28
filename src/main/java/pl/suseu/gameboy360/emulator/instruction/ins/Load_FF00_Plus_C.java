package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_FF00_Plus_C extends Opcode {

    public Load_FF00_Plus_C(){
        super("LD ( ( A,(FF00+C) / (FF00+C),A )",
                (emulator, instruction) -> {}, // fetch
                (gb, ins) -> {
                    int direction = (gb.getValueAtPc() >>> 4) & 0b1;
                    int addrToAdd = gb.getRegisters().getC();

                    if (direction == 0) {
                        gb.getMemoryController().setValue(0xFF00 + addrToAdd, gb.getRegisters().getA());
                    }
                    if (direction == 1) {
                        gb.getRegisters().setA(gb.getValueAt(0xFF00 + addrToAdd));
                    }
                    gb.incrementPc();
                });
    }

}
