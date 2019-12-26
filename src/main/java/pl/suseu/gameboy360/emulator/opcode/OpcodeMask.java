package pl.suseu.gameboy360.emulator.opcode;

public class OpcodeMask {

    private int[] bits;

    public OpcodeMask(int... bits) {
        if (bits.length != 8) {
            System.err.println("Mask must be 8 bits long!");
            System.exit(1);
        }
        this.bits = bits;
    }

    public int[] getBits() {
        return bits;
    }

    public boolean matches(int op) {
        for (int i = 7; i >= 0; i--) {
            int bit1 = this.bits[i];
            int bit2 = op & 0b1;
            op >>>= 1;

            if (bit1 != -1 && bit1 != bit2) // !(bit1 == -1 || bit1 == bit2)
                return false;
        }
        return true;
    }



}
