package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_8Bit_Value_To_Reg  extends Opcode {

    public Load_8Bit_Value_To_Reg(){
        super("LD R,N(8)" ,
                (gb, ins) -> {

                });
    }

}
