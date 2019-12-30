package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_16bit_Addr_And_A extends Opcode {

    public Load_16bit_Addr_And_A(){
        super("LD ( (nn),A )/( A,(nn) )",
                (gb, ins) -> {
                    int dir = (gb.getValueAtPc() >>> 4) & 1;
                    ins.setMem(0, dir);
                },
                (gb, ins) -> {
                    ins.setMem(1, gb.incrementPcAndGetValueAtPc());
                },
                (gb, ins) -> {
                    ins.setMem(2, gb.incrementPcAndGetValueAtPc());
                },
                (gb, ins) -> {
                    int dir = ins.getMem(0);
                    int lower = ins.getMem(1);
                    int upper = ins.getMem(2);

                    int addr = (upper << 8) | lower;

                    if (dir == 0) {
                        gb.getMemoryController().setValue(addr, gb.getRegisters().getA());
                    } else {
                        gb.getRegisters().setA(gb.getValueAt(addr));
                    }
                    gb.incrementPc();
                });
    }

}
