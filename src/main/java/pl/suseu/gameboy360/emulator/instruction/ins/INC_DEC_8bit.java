package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.instruction.ins.utils.RegisterUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class INC_DEC_8bit extends Opcode {

    public INC_DEC_8bit() {
        super("INC/DEC (8bit)",
                (gb, ins) -> {
                    int op = gb.getValueAtPc();
                    int opID = op & 1; //1 - DEC, 0 - INC
                    ins.setMem(0, opID);
                    int register = (op >>> 3) & 0b111;
                    RegisterUtils.Register reg = RegisterUtils.Register.get(register);

                    if (reg == null) {
                        //that message is whatever bcs it will never happen haha!
                        System.err.println("Something went completely wrong with registers! LOOL");
                        System.exit(1);
                    }

                    gb.getRegisters().setOperationFlag(1);

                    if (reg != RegisterUtils.Register.HL_ADDR) {
                        int val = RegisterUtils.getFromReg(gb, reg);

                        setHalfCarry(gb, opID, val);

                        val += opID == 0 ? 1 : -1;

                        RegisterUtils.updateReg(gb, reg, val);

                        if (val == 0) gb.getRegisters().setZeroFlag(1);

                        ins.setFinished(true);
                        gb.incrementPc();
                    }
                }, (gb, ins) -> {
                    int opID = ins.getMem(0);
                    int val = RegisterUtils.getFromReg(gb, RegisterUtils.Register.HL_ADDR);
                    ins.setMem(1, val);
                    setHalfCarry(gb, opID, val);
                    val += opID == 0 ? 1 : -1;
                    if (val == 0) gb.getRegisters().setZeroFlag(1);
                    ins.setMem(2, val);
                }, (gb, ins) -> {
                    RegisterUtils.updateReg(gb, RegisterUtils.Register.HL_ADDR, ins.getMem(2));
                    gb.incrementPc();
                });
    }

    private static void setHalfCarry(GBEmulator gb, int opID, int val){
        if (opID == 0) {
            if (val == 15)
                gb.getRegisters().setHalfCarryFlag(1);
            else
                gb.getRegisters().setHalfCarryFlag(0);
        } else {
//            if ((((val) ^ (val - 1)) & 0b10000) != 0) { // abomination
            if((val & 0b1111) == 0) { // less abomination
                gb.getRegisters().setHalfCarryFlag(1);
            } else {
                gb.getRegisters().setHalfCarryFlag(0);
            }
        }
    }

}
