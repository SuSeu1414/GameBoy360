package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_16Bit_Value_To_Reg extends Opcode {

    public Load_16Bit_Value_To_Reg() {
        super("LD R,nn",
                (gb, ins) -> {
                    // 0 - BC, 1 - DE, 2 - HL, 3 - SP
                    int mode = (gb.getValueAtPc() >> 4) & 0b11;
                    ins.setMem(0, mode);

                    gb.incrementPc();
                    if (mode == 0)
                        gb.getRegisters().setC(gb.incrementPcAndGetValueAtPc());
                    if (mode == 1)
                        gb.getRegisters().setE(gb.incrementPcAndGetValueAtPc());
                    if (mode == 2)
                        gb.getRegisters().setL(gb.incrementPcAndGetValueAtPc());
                    if (mode == 3)
                        gb.getRegisters().setLowerSp(gb.incrementPcAndGetValueAtPc());
                },
                (gb, ins) -> {
                    int mode = ins.getMem(0);

                    if (mode == 0)
                        gb.getRegisters().setB(gb.getValueAt(gb.getPc() - 1));
                    if (mode == 1)
                        gb.getRegisters().setD(gb.getValueAt(gb.getPc() - 1));
                    if (mode == 2)
                        gb.getRegisters().setH(gb.getValueAt(gb.getPc() - 1));
                    if (mode == 3)
                        gb.getRegisters().setUpperSp(gb.getValueAt(gb.getPc() - 1));

                    gb.incrementPc();
                });
    }
}
