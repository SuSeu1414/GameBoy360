package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_FF00_Plus_N extends Opcode {

    public Load_FF00_Plus_N() {
        super("LD ( ( A,(FF00+N) / (FF00+N),A )",
                (emulator, instruction) -> {}, // fetch
                (gb, ins) -> {
                    int direction = (gb.getValueAtPc() >>> 4) & 0b1;
                    int addrToAdd = gb.incrementPcAndGetValueAtPc();
                    ins.setMem(0, direction);
                    ins.setMem(1, addrToAdd);
                },
                (gb, ins) -> {
                    int direction = ins.getMem(0);
                    int addrToAdd = ins.getMem(1);

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
