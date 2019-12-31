package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Return extends Opcode {

    public Return(){
        super("RET", (gb, instruction) -> {}, // fetch
                (gb, ins) -> {
                    int lower = gb.getValueAt(gb.getRegisters().getSp());
                    gb.getRegisters().setSp(gb.getRegisters().getSp() + 1);
                    ins.setMem(0, lower);
                }, (gb, ins) -> {
                    int upper = gb.getValueAt(gb.getRegisters().getSp());
                    gb.getRegisters().setSp(gb.getRegisters().getSp() + 1);
                    ins.setMem(1, upper);
                }, (gb, ins) -> {
                    int lower = ins.getMem(0);
                    int upper = ins.getMem(1);
                    gb.getRegisters().setPc((lower << 8) | upper);
                });
    }

}
