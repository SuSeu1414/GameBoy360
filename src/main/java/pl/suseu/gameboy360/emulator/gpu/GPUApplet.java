package pl.suseu.gameboy360.emulator.gpu;

import pl.suseu.gameboy360.Main;
import pl.suseu.gameboy360.emulator.GBEmulator;
import processing.core.PApplet;

public class GPUApplet extends PApplet {

    private GBEmulator gb;

    public GPUApplet(GBEmulator gb) {
        this.gb = gb;
    }

    @Override
    public void settings() {
        size(160*4, 144*4);
    }

    @Override
    public void setup() {
        gb.setAppletReady(true);
    }

    @Override
    public void draw() {

    }
}
