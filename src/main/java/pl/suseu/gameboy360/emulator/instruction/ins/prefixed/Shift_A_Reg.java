package pl.suseu.gameboy360.emulator.instruction.ins.prefixed;

import pl.suseu.gameboy360.emulator.instruction.ins.utils.RegisterUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Shift_A_Reg extends Opcode {

    public Shift_A_Reg(){
        super("SdA reg",
                (gb, ins) -> {
                    int dest = gb.getValueAtPc() & 0b111;
                    int dir = gb.getValueAtPc() >>> 3;
                    dir &= 1;
                    RegisterUtils.Register reg = RegisterUtils.Register.get(dest);
                    gb.incrementPc();

                    if (reg != RegisterUtils.Register.HL_ADDR) {
                        if (dir == 0)
                            PrefixedUtils.shiftLeft(gb, reg);
                        else
                            PrefixedUtils.shiftRightA(gb, reg);
                        ins.setFinished(true);
                    } else {
                        ins.setMem(0, dest);
                        ins.setMem(1, dir);
                    }
                },
                (gb, ins) -> {
                },
                (gb, ins) -> {
                    int dest = ins.getMem(0);
                    int dir = ins.getMem(1);
                    RegisterUtils.Register reg = RegisterUtils.Register.get(dest);
                    if (dir == 0)
                        PrefixedUtils.shiftLeft(gb, reg);
                    else
                        PrefixedUtils.shiftRightA(gb, reg);
                });
    }

}
