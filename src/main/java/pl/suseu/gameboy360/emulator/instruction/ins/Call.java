package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Call extends Opcode {

    public Call() {
        super("Call",
                (gb, ins) -> {
                    int addr1 = gb.incrementPcAndGetValueAtPc();
                    int addr2 = gb.incrementPcAndGetValueAtPc();
                    int addr = addr2 << 8 | addr1;
                    gb.incrementPc();
                    ins.setMem(0, gb.getPc());
                    gb.setPc(addr);
                }, (gb, instruction) -> {
                }, // wait a cycle
                (gb, instruction) -> {
                }, // wait a cycle
                (gb, instruction) -> {
                }, // wait a cycle
                (gb, instruction) -> {
                }, // wait a cycle
                (gb, ins) -> {
                    int oldPc = ins.getMem(0);
                    gb.getRegisters().setSp(gb.getRegisters().getSp() - 2);
                    gb.getMemoryController().setValue(gb.getRegisters().getSp(), oldPc & 0xFF00);
                    gb.getMemoryController().setValue(gb.getRegisters().getSp() + 1, oldPc & 0x00FF);
                });
    }


}
