package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.instruction.ins.condition.ConditionUtils;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class JumpR extends Opcode {

    public JumpR() {
        super("JR",
                (gb, ins) -> {
                    int op = gb.getValueAtPc();
                    int addrR = (byte) gb.incrementPcAndGetValueAtPc();
                    ins.setMem(0, addrR);
                    gb.incrementPc();

                    boolean conditionMet;

                    if (op >>> 5 == 0) {
                        GBEmulator.debug("No condition needed");
                        conditionMet = true;
                    } else {
                        int cond = (op >>> 3) & 0b11;
                        ConditionUtils.Condition condition = ConditionUtils.Condition.get(cond);
                        if (condition == null) {
                            System.err.println("Something went wrong with condition check!");
                            System.exit(1);
                        }
                        conditionMet = ConditionUtils.checkCondition(gb, condition);
                        GBEmulator.debug("Condition: " + condition.toString() + " - " + conditionMet);
                    }

                    if (!conditionMet) {
                        ins.setFinished(true);
                    }
                },
                (gb, ins) -> {
                    GBEmulator.debug("Jumping...");
                    int addrR = ins.getMem(0);
                    gb.getRegisters().setPc(gb.getPc() + addrR);
                });
    }

}
