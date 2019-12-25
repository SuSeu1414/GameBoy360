package pl.suseu.gameboy360.emulator;

import pl.suseu.gameboy360.emulator.instruction.Instruction;

public class CPU {

    private GBEmulator emulator;
    private Registers registers;

    private Instruction instruction;

    public CPU(GBEmulator emulator){
        this.emulator = emulator;
        registers = new Registers();
    }

    //one cycle @ 1MHz
    public void tick() {
        if (instruction == null || instruction.isFinished()) {
            //TODO: fetch
            return;
        }
        instruction.doStep(this);
    }

    public Registers getRegisters() {
        return registers;
    }
}
