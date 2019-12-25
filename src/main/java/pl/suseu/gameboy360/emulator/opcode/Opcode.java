package pl.suseu.gameboy360.emulator.opcode;

import pl.suseu.gameboy360.emulator.instruction.Step;

public class Opcode {

    private String name;
    private Step[] steps;

    public Opcode(String name, Step... steps) {
        this.name = name;
        this.steps = steps;
    }

    public String getName() {
        return name;
    }

    public Step[] getSteps() {
        return steps;
    }
}
