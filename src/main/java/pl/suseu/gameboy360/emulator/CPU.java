package pl.suseu.gameboy360.emulator;

import pl.suseu.gameboy360.emulator.instruction.Instruction;
import pl.suseu.gameboy360.emulator.opcode.Opcode;
import pl.suseu.gameboy360.emulator.opcode.OpcodeFetcher;

public class CPU {

    private long ticks = 0;
    private GBEmulator gb;
    private Registers registers;

    private OpcodeFetcher fetcher;
    private Instruction instruction;

    public CPU(GBEmulator gb){
        this.gb = gb;
        this.fetcher = new OpcodeFetcher(gb);
        this.registers = new Registers();
    }

    //one cycle @ 1MHz
    public void tick() {
        ticks++;
        if (instruction == null || instruction.isFinished()) {
            Opcode opcode = fetcher.fetch();
            instruction = new Instruction(opcode.getSteps());

            if (GBEmulator.DEBUG) {
                System.out.println("\u001B[36m Fetch...");
                System.out.print("[0x" + Integer.toHexString(registers.getPc()) + "] " +
                        "0x" + Integer.toHexString(gb.getValueAtPc()) + ": ");
                System.out.println("" + opcode.getName() + "\u001B[0m");
            }
        }
        if (GBEmulator.DEBUG){
            System.out.println("Step... (" + ticks + ")");
        }
        instruction.doStep(gb);
//        if (ticks == -1)
//            System.exit(1);
//        if (gb.getPc() == 0x6b)
//            ticks = -2;

//        if (ticks % 4_000_000 == 0) {
//            System.out.println("LOL");
//        }
    }

    public OpcodeFetcher getOpcodeFetcher() {
        return fetcher;
    }

    public Registers getRegisters() {
        return registers;
    }
}
