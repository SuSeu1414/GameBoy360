package pl.suseu.gameboy360.emulator;

import pl.suseu.gameboy360.emulator.gpu.GPU;
import pl.suseu.gameboy360.emulator.gpu.GPUApplet;
import pl.suseu.gameboy360.emulator.memory.MemoryController;
import processing.core.PApplet;

public class GBEmulator {

    public static boolean DEBUG = true;

    private GPUApplet gpuApplet;
    private boolean appletReady = false;

    private CPU cpu;
    private GPU gpu;

    private MemoryController memoryController;

    public GBEmulator(){
        memoryController = new MemoryController(this);
        memoryController.loadCartridge("roms/DrMario.gb");
        cpu = new CPU(this);

        gpuApplet = new GPUApplet(this);
        Thread appletThread = new Thread(() -> {
            PApplet.runSketch(new String[]{"GameBoy360"}, gpuApplet);
        });
        appletThread.setName("PApplet");
        appletThread.start();
    }

    public void tick(){
        cpu.tick();
    }

    public int getPc(){
        return getRegisters().getPc();
    }

    public void setPc(int addr){
        getRegisters().setPc(addr);
    }

    public void incrementPc(){
        getRegisters().incPc();
    }

    public int incrementPcAndReturn(){
        incrementPc();
        return getRegisters().getPc();
    }

    public int getValueAt(int address) {
        return memoryController.getValue(address);
    }

    public int getValueAtPc(){
        return getValueAt(cpu.getRegisters().getPc());
    }

    public int incrementPcAndGetValueAtPc(){
        incrementPc();
        return getValueAtPc();
    }

    public boolean isReady(){
        return appletReady;
    }

    //---

    public static void debug(String s) {
        if (DEBUG) {
            System.out.println(s);
        }
    }

    public void setGpuApplet(GPUApplet gpuApplet) {
        this.gpuApplet = gpuApplet;
    }

    public GPUApplet getGpuApplet() {
        return gpuApplet;
    }

    public Registers getRegisters(){
        return cpu.getRegisters();
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setAppletReady(boolean appletReady) {
        this.appletReady = appletReady;
    }

    public MemoryController getMemoryController() {
        return memoryController;
    }
}
