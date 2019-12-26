package pl.suseu.gameboy360.emulator.opcode;

import pl.suseu.gameboy360.emulator.instruction.ins.ALU_Reg;
import pl.suseu.gameboy360.emulator.instruction.ins.Load_16Bit_Value_To_Reg;
import pl.suseu.gameboy360.emulator.instruction.ins.Load_SP_To_Address;
import pl.suseu.gameboy360.emulator.instruction.ins.NOP;

import java.util.HashMap;
import java.util.Map;

public class Opcodes {

    private static Map<OpcodeMask, Opcode> opcodes = new HashMap<>();


    public static void init(){
        opcodes.put(new OpcodeMask(0, 0, 0, 0, 0, 0, 0, 0), new NOP());
        opcodes.put(new OpcodeMask(0,0,0,0,1,0,0,0), new Load_SP_To_Address());
        opcodes.put(new OpcodeMask(0, 0, -1, -1, 0, 0, 0, 1), new Load_16Bit_Value_To_Reg());
        opcodes.put(new OpcodeMask(1, 0, -1, -1, -1, -1, -1, -1), new ALU_Reg());
    }

    public static Map<OpcodeMask, Opcode> getOpcodes() {
        return opcodes;
    }
}
