package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

/*
    Opcodes:
    0x1
    0x11
    0x21
    0x31
 */
public class Load_16Bit_Value_To_Reg extends Opcode {

    public Load_16Bit_Value_To_Reg() {
        super("LD R,nn",
                (gb, ins) -> {
                    // 0 - BC, 1 - DE, 2 - HL, 3 - SP
                    int mode = (gb.getValueAtPc() >> 4) & 0b11;
                    ins.setMem(0, mode);

                    gb.incrementPc();
                    int val = gb.incrementPcAndGetValueAtPc();
                    GBEmulator.debug("mode="+mode +", " +
                            "pc=0x"+Integer.toHexString(gb.getPc()) + ", " +
                            "val=0x"+ Integer.toHexString(val));

                    if (mode == 0)
                        gb.getRegisters().setC(val);
                    if (mode == 1)
                        gb.getRegisters().setE(val);
                    if (mode == 2)
                        gb.getRegisters().setL(val);
                    if (mode == 3)
                        gb.getRegisters().setLowerSp(val);
                },
                (gb, ins) -> {
                    int mode = ins.getMem(0);

                    int val = gb.getValueAt(gb.getPc() - 1);
                    GBEmulator.debug("mode="+mode +", " +
                            "pc=0x"+Integer.toHexString(gb.getPc()) + ", " +
                            "val=0x"+ Integer.toHexString(val));

                    if (mode == 0)
                        gb.getRegisters().setB(val);
                    if (mode == 1)
                        gb.getRegisters().setD(val);
                    if (mode == 2)
                        gb.getRegisters().setH(val);
                    if (mode == 3)
                        gb.getRegisters().setUpperSp(val);

                    gb.incrementPc();
                });
    }
}
