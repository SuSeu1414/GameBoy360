package pl.suseu.gameboy360.emulator.instruction;

import pl.suseu.gameboy360.emulator.CPU;
import pl.suseu.gameboy360.emulator.GBEmulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instruction {

    private int nextStep = 0;
    private Map<Integer, Integer> stepMemory = new HashMap<>();

    private Step[] steps;
    private boolean finished = false;

    public Instruction(Step... steps){
        this.steps = steps;
    }

    public void doStep(GBEmulator emulator) {
        steps[nextStep].execute(emulator, this);
        nextStep++;
        if (steps.length <= nextStep) finished = true;
    }

    public Step[] getSteps() {
        return steps;
    }

    public void setMem(int index, int value){
        stepMemory.put(index, value);
    }

    public int getMem(int index) {
        return stepMemory.get(index);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
