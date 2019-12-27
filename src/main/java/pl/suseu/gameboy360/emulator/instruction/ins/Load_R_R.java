package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.instruction.ins.utils.RegisterUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_R_R extends Opcode {

    public Load_R_R() {
        super("LD R,R",
                (gb, ins) -> {
                    int op = gb.getValueAtPc();
                    int regFrom = op & 0b111;
                    int regTo = (op >>> 3) & 0b111;
                    RegisterUtils.Register registerFrom = RegisterUtils.Register.get(regFrom);
                    RegisterUtils.Register registerTo = RegisterUtils.Register.get(regTo);

                    if (registerFrom == null || registerTo == null) {
                        System.err.println("Something went wrong with reading register!");
                        System.exit(1);
                    }

                    if (registerTo != RegisterUtils.Register.HL_ADDR
                            && registerFrom != RegisterUtils.Register.HL_ADDR) {
                        RegisterUtils.updateReg(gb, registerTo, RegisterUtils.getFromReg(gb, registerFrom));
                        gb.incrementPc();
                        ins.setFinished(true);
                    } else {
                        ins.setMem(0, regFrom);
                        ins.setMem(1, regTo);
                    }
                },
                (gb, ins) -> {
                    int regFrom = ins.getMem(0);
                    int regTo = ins.getMem(1);
                    RegisterUtils.Register registerFrom = RegisterUtils.Register.get(regFrom);
                    RegisterUtils.Register registerTo = RegisterUtils.Register.get(regTo);

                    if (registerFrom == null || registerTo == null) {
                        System.err.println("Something went wrong with reading register!");
                        System.exit(1);
                    }

                    RegisterUtils.updateReg(gb, registerTo, RegisterUtils.getFromReg(gb, registerFrom));
                    gb.incrementPc();
                    ins.setFinished(true);
                });
    }

}
