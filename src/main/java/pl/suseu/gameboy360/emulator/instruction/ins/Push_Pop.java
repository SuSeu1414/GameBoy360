package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Push_Pop extends Opcode {

    public Push_Pop() {
        super("POP / PUSH",
                (gb, ins) -> {
                    int op = gb.getValueAtPc();
                    int mode = (op >>> 2) & 1; // 0-pop, 1-push
                    int reg = (op >>> 4) & 0b11; // 0 - BC, 1 - DE, 2 - HL, 3 - AF

                    GBEmulator.debug("mode=" + mode + ", regMode=" + reg);

                    ins.setMem(0, mode);
                    ins.setMem(1, reg);
                    gb.incrementPc();
                },
                (gb, ins) -> {
                    int mode = ins.getMem(0);
                    int reg = ins.getMem(1);

                    if (mode == 0) {
                        int sp = gb.getRegisters().getSp();
                        int val = gb.getValueAt(sp);

                        if (reg == 0)
                            gb.getRegisters().setC(val);
                        else if (reg == 1)
                            gb.getRegisters().setE(val);
                        else if (reg == 2)
                            gb.getRegisters().setL(val);
                        else if (reg == 3)
                            gb.getRegisters().setF(val);

                        sp++;
                        gb.getRegisters().setSp(sp);
                    }
                }, (gb, ins) -> {
                    int mode = ins.getMem(0);
                    int reg = ins.getMem(1);

                    int sp = gb.getRegisters().getSp();
                    if (mode == 0) {
                        int val = gb.getValueAt(sp);

                        if (reg == 0)
                            gb.getRegisters().setB(val);
                        else if (reg == 1)
                            gb.getRegisters().setD(val);
                        else if (reg == 2)
                            gb.getRegisters().setH(val);
                        else if (reg == 3)
                            gb.getRegisters().setA(val);

                        sp++;
                        gb.getRegisters().setSp(sp);
                        ins.setFinished(true);
                    } else {
                        sp--;
                        gb.getRegisters().setSp(sp);

                        if (reg == 0)
                            gb.getMemoryController().setValue(sp , gb.getRegisters().getB());
                        if (reg == 1)
                            gb.getMemoryController().setValue(sp , gb.getRegisters().getD());
                        if (reg == 2)
                            gb.getMemoryController().setValue(sp , gb.getRegisters().getH());
                        if (reg == 3)
                            gb.getMemoryController().setValue(sp , gb.getRegisters().getA());
                    }
                }, (gb, ins) -> {
                    int reg = ins.getMem(1);

                    int sp = gb.getRegisters().getSp();
                    sp--;
                    gb.getRegisters().setSp(sp);

                    if (reg == 0)
                        gb.getMemoryController().setValue(sp , gb.getRegisters().getC());
                    if (reg == 1)
                        gb.getMemoryController().setValue(sp , gb.getRegisters().getE());
                    if (reg == 2)
                        gb.getMemoryController().setValue(sp , gb.getRegisters().getL());
                    if (reg == 3)
                        gb.getMemoryController().setValue(sp , gb.getRegisters().getF());
                });
    }
}
