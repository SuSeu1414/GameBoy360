package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_From_Address_Register_And_A extends Opcode {

    public Load_From_Address_Register_And_A() {
        super("LD ( (R),A )/( A,(R) )",
                (emulator, instruction) -> {
                }, // fetch
                (gb, ins) -> {
                    int op = gb.getValueAtPc();
                    int dir = (op >>> 3) & 1;
                    int reg = (op >>> 4) & 1;

                    if (dir == 0) {
                        int val;
                        if (reg == 0)
                            val = gb.getRegisters().getBC();
                        else
                            val = gb.getRegisters().getDE();
                        gb.getRegisters().setA(val);
                    } else {
                        int val = gb.getRegisters().getA();
                        int addr = reg == 0 ? gb.getRegisters().getBC() : gb.getRegisters().getDE();
                        gb.getMemoryController().setValue(addr, val);
                    }
                    gb.incrementPc();
                });
    }

}
