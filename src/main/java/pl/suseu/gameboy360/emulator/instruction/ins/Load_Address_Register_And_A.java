package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_Address_Register_And_A extends Opcode {

    public Load_Address_Register_And_A() {
        super("LD ( (R),A )/( A,(R) )",
                (emulator, instruction) -> {
                }, // fetch
                (gb, ins) -> {
                    int op = gb.getValueAtPc();
                    int dir = (op >>> 3) & 1;
                    int reg = (op >>> 4) & 1;

                    if (dir == 0) {
                        int val = gb.getRegisters().getA();
                        int addr;
                        if (reg == 0)
                            addr = gb.getRegisters().getBC();
                        else
                            addr = gb.getRegisters().getDE();
                        gb.getMemoryController().setValue(addr, val);
                    } else {
                        int addr = reg == 0 ? gb.getRegisters().getBC() : gb.getRegisters().getDE();
                        int val = gb.getValueAt(addr);
                        gb.getRegisters().setA(val);
                    }
                    gb.incrementPc();
                });
    }

}
