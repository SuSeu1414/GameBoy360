package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class INC_DEC_16bit extends Opcode {

    public INC_DEC_16bit() {
        super("INC/DEC (16bit)",
                (gb, ins) -> {
                    int mode = (gb.getValueAtPc() >>> 3) & 1; //0-inc, 1-dec
                    int reg = (gb.getValueAtPc() >>> 4) & 0b11; //0-bc, 1-de, 2-hl, 3-sp

                    ins.setMem(0, mode);
                    ins.setMem(1, reg);
                }, (gb, ins) -> {
                    int mode = ins.getMem(0);
                    int reg = ins.getMem(1);
                    int val = 0;

                    if (reg == 0)
                        val = gb.getRegisters().getBC();
                    if (reg == 1)
                        val = gb.getRegisters().getDE();
                    if (reg == 2)
                        val = gb.getRegisters().getHL();
                    if (reg == 3)
                        val = gb.getRegisters().getSp();

                    val += (mode == 0 ? 1 : -1);

                    if (reg == 0)
                        gb.getRegisters().setBC(val);
                    if (reg == 1)
                        gb.getRegisters().setDE(val);
                    if (reg == 2)
                        gb.getRegisters().setHL(val);
                    if (reg == 3)
                        gb.getRegisters().setSp(val);

                    gb.incrementPc();
                });
    }

}
