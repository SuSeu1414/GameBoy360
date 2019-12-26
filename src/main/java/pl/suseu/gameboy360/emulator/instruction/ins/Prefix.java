package pl.suseu.gameboy360.emulator.instruction.ins;

import pl.suseu.gameboy360.emulator.opcode.Opcode;

public class Prefix extends Opcode {

    public Prefix(){
        super("PREFIX",
                (gb, ins) -> {
                    gb.getCpu().getOpcodeFetcher().setPrefixed(true);
                    gb.incrementPc();
                });
    }

}
