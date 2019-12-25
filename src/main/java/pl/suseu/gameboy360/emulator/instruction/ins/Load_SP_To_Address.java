package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_SP_To_Address extends Opcode {
    public Load_SP_To_Address() {
        super("LD (nn),SP",
                (gb, ins) -> {
                    ins.setMem(0, gb.incrementPcAndGetValueAtPc()); //lower bits
                },
                (gb, ins) -> {
                    ins.setMem(1, gb.incrementPcAndGetValueAtPc()); //upper bits
                },
                (gb, ins) -> {
                    gb.getRegisters().setLowerSp(ins.getMem(0));
                },
                (gb, ins) -> {
                    gb.getRegisters().setUpperSp(ins.getMem(1));
                    gb.incrementPc();
                });
    }
}
