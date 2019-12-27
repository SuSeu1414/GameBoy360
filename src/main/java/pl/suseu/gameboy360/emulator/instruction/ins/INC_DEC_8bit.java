package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.instruction.ins.utils.RegisterUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class INC_DEC_8bit extends Opcode {

    public INC_DEC_8bit() {
        super("INC/DEC (8bit)",
                (gb, ins) -> {
                    int op = gb.getValueAtPc();
                    int opID = op & 1; //1 - DEC, 0 - INC
                    int register = (op >>> 3) & 0b111;
                    RegisterUtils.Register reg = RegisterUtils.Register.get(register);

                    if (opID == 0) {

                    } else {

                    }

                    gb.incrementPc();
                });
    }

}
