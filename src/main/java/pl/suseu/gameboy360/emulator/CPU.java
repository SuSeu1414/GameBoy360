package pl.suseu.gameboy360.emulator;

import pl.suseu.gameboy360.emulator.instruction.Instruction;
import pl.suseu.gameboy360.emulator.opcode.Opcode;
import pl.suseu.gameboy360.emulator.opcode.OpcodeFetcher;

public class CPU {

    private GBEmulator emulator;
    private Registers registers;

    private OpcodeFetcher fetcher;
    private Instruction instruction;

    public CPU(GBEmulator emulator){
        this.emulator = emulator;
        this.fetcher = new OpcodeFetcher(emulator);
        this.registers = new Registers();
    }

    //one cycle @ 1MHz
    public void tick() {
        if (instruction == null || instruction.isFinished()) {
            Opcode opcode = fetcher.fetch();
            instruction = new Instruction(opcode.getSteps());

            if (GBEmulator.DEBUG) {
                System.out.println("\u001B[36m Fetch...");
                System.out.print("[0x" + Integer.toHexString(registers.getPc()) + "] " +
                        "0x" + Integer.toHexString(emulator.getValueAtPc()) + ": ");
                System.out.println("" + opcode.getName() + "\u001B[0m");
            }

            return;
        }
        if (GBEmulator.DEBUG){
            System.out.println("Step...");
        }
        instruction.doStep(emulator);
    }

    public OpcodeFetcher getOpcodeFetcher() {
        return fetcher;
    }

    public Registers getRegisters() {
        return registers;
    }
}
