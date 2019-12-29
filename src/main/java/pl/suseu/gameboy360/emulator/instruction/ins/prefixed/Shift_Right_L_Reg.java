package pl.suseu.gameboy360.emulator.instruction.ins.prefixed;

import pl.suseu.gameboy360.emulator.instruction.ins.utils.RegisterUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Shift_Right_L_Reg extends Opcode {

    public Shift_Right_L_Reg() {
        super("SRL reg",
                (gb, ins) -> {
                    int dest = gb.getValueAtPc() & 0b111;
                    RegisterUtils.Register reg = RegisterUtils.Register.get(dest);
                    gb.incrementPc();

                    if (reg != RegisterUtils.Register.HL_ADDR) {
                        PrefixedUtils.shiftRightL(gb, reg);
                        ins.setFinished(true);
                    } else {
                        ins.setMem(0, dest);
                    }
                },
                (gb, ins) -> {
                },
                (gb, ins) -> {
                    int dest = ins.getMem(0);
                    RegisterUtils.Register reg = RegisterUtils.Register.get(dest);
                    PrefixedUtils.shiftRightL(gb, reg);
                });
    }

}
