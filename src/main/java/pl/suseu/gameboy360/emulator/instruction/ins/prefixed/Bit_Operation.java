package pl.suseu.gameboy360.emulator.instruction.ins.prefixed;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Bit_Operation extends Opcode {

    public Bit_Operation(){
        super("BIT/RES/SET N,D",
                (gb, ins) -> {
                    int dest = gb.getValueAtPc() & 0b111;
                    int bit = (gb.getValueAtPc() >>> 3) & 0b111;
                    int op = (gb.getValueAtPc() >>> 6) & 0b11;
                    PrefixedUtils.Register reg = PrefixedUtils.Register.get(dest);
                    Operation operation = Operation.get(op);

                    if (reg == null) {
                        System.err.println("BIT_OP REGISTER IS NULL" +
                                "(0b" + Integer.toBinaryString(dest) + ")");
                        System.exit(1);
                    }

                    if (operation == null) {
                        System.err.println("BIT OPERATION IS NULL" +
                                "(0b" + Integer.toBinaryString(op) + ")");
                        System.exit(1);
                    }

                    GBEmulator.debug("operation="+operation.toString()+", " +
                            "bit=" + bit + ", " +
                            "register="+reg.toString());

                    if (reg != PrefixedUtils.Register.HL_ADDR) {
                        if (operation == Operation.TEST) {
                            PrefixedUtils.testBit(gb, reg, bit, 0);
                        }
                        if (operation == Operation.RES) {
                            PrefixedUtils.resetBit(gb, reg, bit, 0);
                        }
                        if (operation == Operation.SET) {
                            PrefixedUtils.setBit(gb, reg, bit, 0);
                        }
                        gb.incrementPc();
                        ins.setFinished(true);
                    }
                },
                (gb, ins) -> {
                    //no need to check if it's HL_ADDR
                    int bit = (gb.getValueAtPc() >>> 3) & 0b111;
                    int op = (gb.getValueAtPc() >>> 6) & 0b11;
                    PrefixedUtils.Register reg = PrefixedUtils.Register.HL_ADDR;
                    Operation operation = Operation.get(op);
                    int addr = gb.getRegisters().getHL();

                    if (operation == Operation.TEST) {
                        PrefixedUtils.testBit(gb, reg, bit, addr);
                    }
                    if (operation == Operation.RES) {
                        PrefixedUtils.resetBit(gb, reg, bit, addr);
                    }
                    if (operation == Operation.SET) {
                        PrefixedUtils.setBit(gb, reg, bit, addr);
                    }
                    gb.incrementPc();
                });
    }

    private enum Operation {
        TEST(0b01), RES(0b10), SET(0b11);

        final int op;

        Operation(int op) {
            this.op = op;
        }

        public int getOp() {
            return op;
        }

        public static Operation get(int op) {
            for (Operation o : Operation.values()) {
                if (o.getOp() == op) return o;
            }
            return null;
        }
    }

}
