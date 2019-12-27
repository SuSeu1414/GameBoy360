package pl.suseu.gameboy360.emulator.opcode;

import pl.suseu.gameboy360.emulator.instruction.ins.*;
import pl.suseu.gameboy360.emulator.instruction.ins.prefixed.Bit_Operation;

import java.util.HashMap;
import java.util.Map;

public class Opcodes {

    private static Map<OpcodeMask, Opcode> opcodes = new HashMap<>();
    private static Map<OpcodeMask, Opcode> prefixedOpcodes = new HashMap<>();


    public static void init() {
        opcodes.put(new OpcodeMask(0, 0, 0, 0, 0, 0, 0, 0), new NOP());
        opcodes.put(new OpcodeMask(0, 0, 0, 0, 1, 0, 0, 0), new Load_SP_To_Address());
        opcodes.put(new OpcodeMask(0, 0, -1, -1, 0, 0, 0, 1), new Load_16Bit_Value_To_Reg());
        opcodes.put(new OpcodeMask(1, 0, -1, -1, -1, -1, -1, -1), new ALU_Reg());
        opcodes.put(new OpcodeMask(0, 0, 1, -1, -1, 0, 1, 0), new Load_HL_And_Adjust());
        opcodes.put(new OpcodeMask(1, 1, 0, 0, 1, 0, 1, 1), new Prefix());
        opcodes.put(new OpcodeMask(0, 0, 0, 1, 1, 0, 0, 0), new JumpR());
        opcodes.put(new OpcodeMask(0, 0, 1, -1, -1, 0, 0, 0), new JumpR());
        opcodes.put(new OpcodeMask(0, 0, -1, -1, -1, 1, 1, 0), new Load_8Bit_Value_To_Reg());
        opcodes.put(new OpcodeMask(1, 1, 1, -1, 0, 0, 1, 0), new Load_FF00_Plus_N());
        opcodes.put(new OpcodeMask(1, 1, 1, -1, 0, 0, 0, 0), new Load_FF00_Plus_C());
        opcodes.put(new OpcodeMask(0, 1, -1, -1, -1, -1, -1, -1), new Load_R_R());

        prefixedOpcodes.put(new OpcodeMask(0, 1, -1, -1, -1, -1, -1, -1), new Bit_Operation());
        prefixedOpcodes.put(new OpcodeMask(1, 0, -1, -1, -1, -1, -1, -1), new Bit_Operation());
        prefixedOpcodes.put(new OpcodeMask(1, 1, -1, -1, -1, -1, -1, -1), new Bit_Operation());
    }

    public static Map<OpcodeMask, Opcode> getOpcodes() {
        return opcodes;
    }

    public static Map<OpcodeMask, Opcode> getPrefixedOpcodes() {
        return prefixedOpcodes;
    }
}
