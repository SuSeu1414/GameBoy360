package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Load_HL_And_Adjust extends Opcode {

    public Load_HL_And_Adjust() {
        super("LDI/LDD ( (HL-/+),A )/( A,(HL-/+) )",
                (gb, ins) -> {
                    int op = (gb.getValueAtPc() >>> 4) & 1;
                    int dir = (gb.getValueAtPc() >>> 3) & 1;

                    GBEmulator.debug("op=" + (op == 1 ? "DEC" : "INC") + ", " +
                            "dir=" + (dir == 1 ? "RIGHT" : "LEFT"));

                    ins.setMem(0, op);
                    ins.setMem(1, dir);
                },
                (gb, ins) -> {
                    int op = ins.getMem(0);
                    int dir = ins.getMem(1);

                    if (dir == 0) {
                        int val = gb.getRegisters().getA();
                        int address = gb.getRegisters().getHL();
                        gb.getMemoryController().setValue(address, val);
                    } else if (dir == 1) {
                        int address = gb.getRegisters().getHL();
                        int val = gb.getValueAt(address);
                        gb.getRegisters().setA(val);
                    }

                    if (op == 0) {
                        gb.getRegisters().setHL(gb.getRegisters().getHL()+1);
                    } else if (op == 1){
                        gb.getRegisters().setHL(gb.getRegisters().getHL()-1);
                    }

                    gb.incrementPc();
                });
    }
}
