package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.instruction.ins.utils.DestinationUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_8Bit_Value_To_Reg  extends Opcode {

    public Load_8Bit_Value_To_Reg(){
        super("LD R,N(8)" ,
                (gb, ins) -> {
                    int des = (gb.getValueAtPc() >>> 3) & 0b111;
                    DestinationUtils.Register register = DestinationUtils.Register.get(des);
                    int val = gb.incrementPcAndGetValueAtPc();

                    if (register == null) {
                        System.err.println("Register is null");
                        System.exit(1);
                    }

                    DestinationUtils.updateReg(gb, register, val);
                    gb.incrementPc();
                });
    }

}
