package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.GBEmulator;
import pl.suseu.gameboy360.emulator.opcode.Opcode;

/*
    Opcodes:
    0x80 - 0xBF
 */
public class ALU_Reg extends Opcode {

    public ALU_Reg() {
        super("ALU A,D", (gb, ins) -> {
                    int dest = gb.getValueAtPc() & 0b111;
                    int op = (gb.getValueAtPc() & 0b111000) >>> 3;
                    Destination destination = Destination.get(dest);
                    Operation operation = Operation.get(op);

                    if (destination == null) {
                        System.err.println("ALU DESTINATION IS NULL" +
                                "(0b" + Integer.toBinaryString(dest) + ")");
                        System.exit(1);
                    }

                    if (operation == null) {
                        System.err.println("ALU OPERATION IS NULL" +
                                "(0b" + Integer.toBinaryString(op) + ")");
                        System.exit(1);
                    }

                    GBEmulator.debug("ALU op=" + operation.toString() + " destination=" + destination.toString());

                    if (destination != Destination.HL_ADDR) {
                        int val = 0;
                        if (destination == Destination.A)
                            val = gb.getRegisters().getA();
                        if (destination == Destination.B)
                            val = gb.getRegisters().getB();
                        if (destination == Destination.C)
                            val = gb.getRegisters().getC();
                        if (destination == Destination.D)
                            val = gb.getRegisters().getD();
                        if (destination == Destination.E)
                            val = gb.getRegisters().getE();
                        if (destination == Destination.H)
                            val = gb.getRegisters().getH();
                        if (destination == Destination.L)
                            val = gb.getRegisters().getL();

                        int result = calc(gb, val, operation);
                        gb.getRegisters().setA(result);
                        gb.incrementPc();
                        ins.setFinished(true);
                    }
                },
                (gb, ins) -> {
                    Destination destination = Destination.get(gb.getValueAtPc() & 0b111);
                    Operation operation = Operation.get(gb.getValueAtPc() & 0b111000);

                    if (destination != Destination.HL_ADDR) {
                        int val = gb.getValueAt(gb.getRegisters().getHL());
                        int result = calc(gb, val, operation);
                        gb.getRegisters().setA(result);
                        gb.incrementPc();
                        ins.setFinished(true);
                    }
                });
    }

    private static int calc(GBEmulator gb, int val, Operation operation) {
        int result = 0;
        int A = gb.getRegisters().getA();

        GBEmulator.debug("ALU operation... val=" + val + ", a=" + A);

        if (operation == Operation.ADD) {
            result = add(gb, A, val);
        }
        if (operation == Operation.ADC) {
            val += gb.getRegisters().getCarryFlag();
            result = add(gb, A, val);
        }
        if (operation == Operation.SUB) {
            result = sub(gb, A, val);
        }
        if (operation == Operation.SBC) {
            val += gb.getRegisters().getCarryFlag();
            result = sub(gb, A, val);
        }
        if (operation == Operation.AND) {
            gb.getRegisters().resetFlags();
            result = A & val;
            if (result == 0) {
                gb.getRegisters().setZeroFlag(1);
            }
            gb.getRegisters().setHalfCarryFlag(1);
        }
        if (operation == Operation.OR) {
            gb.getRegisters().resetFlags();
            result = A | val;
            if (result == 0) {
                gb.getRegisters().setZeroFlag(1);
            }
        }
        if (operation == Operation.XOR) {
            gb.getRegisters().resetFlags();
            result = A ^ val;
            if (result == 0) {
                gb.getRegisters().setZeroFlag(1);
            }
        }
        if (operation == Operation.CP) {
            sub(gb, A, val); //result is thrown away
        }
        GBEmulator.debug("Result: " + result);
        return result;
    }

    private static int add(GBEmulator gb, int A, int val) {
        int result = (A + val) & 0xFF;
        int carryBits = (A ^ val ^ result);

        gb.getRegisters().resetFlags();
        if (result < A) {
            gb.getRegisters().setCarryFlag(1);
        }
        if ((carryBits & 0b10000) != 0) {
            gb.getRegisters().setHalfCarryFlag(1);
        }
        if (result == 0) {
            gb.getRegisters().setZeroFlag(1);
        }
        return result;
    }

    private static int sub(GBEmulator gb, int A, int val) {
        int result = (A - val) & 0xFF;
        int carryBits = (A ^ val ^ result);

        gb.getRegisters().resetFlags();
        if (result <= A) { // (result > A) - there was borrow
            gb.getRegisters().setCarryFlag(1);
        }
        if ((carryBits & 0b10000) != 0) {
            gb.getRegisters().setHalfCarryFlag(1);
        }
        if (result == 0) {
            gb.getRegisters().setZeroFlag(1);
        }
        return result;
    }

    private enum Destination {
        B(0b000), C(0b001), D(0b010), E(0b011),
        H(0b100), L(0b101), HL_ADDR(0b110), A(0b111);

        final int dest;

        Destination(int dest) {
            this.dest = dest;
        }

        public int getDest() {
            return dest;
        }

        public static Destination get(int dest) {
            for (Destination d : Destination.values()) {
                if (d.getDest() == dest) return d;
            }
            return null;
        }
    }

    private enum Operation {
        ADD(0b000), ADC(0b001), SUB(0b010), SBC(0b011),
        AND(0b100), XOR(0b101), OR(0b110), CP(0b111);

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
