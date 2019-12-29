package pl.suseu.gameboy360.emulator.instruction.ins.prefixed;

import pl.suseu.gameboy360.emulator.instruction.ins.utils.RegisterUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Rotate_Reg extends Opcode {

    public Rotate_Reg(RegisterUtils.Register register) {
        super("Rd reg",
                (gb, ins) -> {
                    int dest = register == null ? gb.getValueAtPc() & 0b111 : register.getReg();
                    int dir = gb.getValueAtPc() >>> 3;
                    dir &= 1;
                    RegisterUtils.Register reg = RegisterUtils.Register.get(dest);
                    gb.incrementPc();

                    if (reg != RegisterUtils.Register.HL_ADDR) {
                        if (dir == 0)
                            PrefixedUtils.rotateLeft(gb, reg);
                        else
                            PrefixedUtils.rotateRight(gb, reg);
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
                        PrefixedUtils.rotateLeft(gb, reg);
                    else
                        PrefixedUtils.rotateRight(gb, reg);
                });
    }

}
