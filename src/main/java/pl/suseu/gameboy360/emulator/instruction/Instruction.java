package pl.suseu.gameboy360.emulator.instruction;

import pl.suseu.gameboy360.emulator.CPU;

public class Instruction {

    private int nextStep = 0;
    private int stepOutput = 0;

    private Step[] steps;
    private boolean finished = false;

    public Instruction(Step... steps){
        this.steps = steps;
    }

    public void doStep(CPU cpu) {
        steps[nextStep].execute(cpu, this);
        nextStep++;
        if (steps.length <= nextStep) finished = true;
    }

    public Step[] getSteps() {
        return steps;
    }

    public int getStepOutput() {
        return stepOutput;
    }

    public void setStepOutput(int stepOutput) {
        this.stepOutput = stepOutput;
    }

    public boolean isFinished() {
        return finished;
    }
}
