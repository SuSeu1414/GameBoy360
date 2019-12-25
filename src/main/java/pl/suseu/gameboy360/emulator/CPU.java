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
                System.out.print("[" + registers.getPc() + "] ");
                System.out.println(opcode.getName());
            }

            return;
        }
        instruction.doStep(emulator);
    }

    public Registers getRegisters() {
        return registers;
    }
}
