package pl.suseu.gameboy360.emulator.opcode;

import pl.suseu.gameboy360.emulator.GBEmulator;

import java.util.Map;

public class OpcodeFetcher {

    private GBEmulator emulator;

    public OpcodeFetcher(GBEmulator emulator) {
        this.emulator = emulator;
    }

    public Opcode fetch(){
        int op = emulator.getValueAtPc();
        for (Map.Entry<OpcodeMask, Opcode> entry : Opcodes.getOpcodes().entrySet()){
            if (entry.getKey().matches(op)) return entry.getValue();
        }
        System.err.println("Unknown opcode: 0x" + Integer.toHexString(op));
        System.exit(2);
        return null;
    }
}
