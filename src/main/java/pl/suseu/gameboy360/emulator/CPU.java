package pl.suseu.gameboy360.emulator;

public class CPU {

    private Registers registers;

    public CPU(){
        registers = new Registers();
    }

    public Registers getRegisters() {
        return registers;
    }
}
