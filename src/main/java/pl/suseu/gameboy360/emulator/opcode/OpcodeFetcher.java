package pl.suseu.gameboy360.emulator.opcode;

import pl.suseu.gameboy360.emulator.GBEmulator;

import java.util.Arrays;
import java.util.Map;

public class OpcodeFetcher {

    private GBEmulator emulator;
    private boolean prefixed;

    public OpcodeFetcher(GBEmulator emulator) {
        this.emulator = emulator;
    }

    public Opcode fetch(){
        int op = emulator.getValueAtPc();

        Map<OpcodeMask, Opcode> ops = prefixed ? Opcodes.getPrefixedOpcodes() : Opcodes.getOpcodes();

        for (Map.Entry<OpcodeMask, Opcode> entry : ops.entrySet()){
            if (entry.getKey().matches(op)) {
                if (prefixed) prefixed = false;
                return entry.getValue();
            }
        }
        System.err.println("Unknown opcode: 0x" + Integer.toHexString(op));
        System.err.println("Prefixed = " + prefixed);
        System.exit(2);
        return null;
    }

    public void setPrefixed(boolean prefixed) {
        this.prefixed = prefixed;
    }

    public boolean isPrefixed() {
        return prefixed;
    }
}
