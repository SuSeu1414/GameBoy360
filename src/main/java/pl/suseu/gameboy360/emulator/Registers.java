package pl.suseu.gameboy360.emulator;

public class Registers {

    private int a;
    private int f;

    private int b;
    private int c;

    private int d;
    private int e;

    private int h;
    private int l;

    private int sp;
    private int pc;

    public int getAF() {
        return a << 8 | f;
    }

    public void setAF(int af) {
        a = af >> 8;
        f = af & 0xff;
    }

    public int getBC(){
        return b << 8 | c;
    }

    public void setBC(int bc) {
        b = bc >> 8;
        c = bc & 0xff;
    }

    public int getDE(){
        return d << 8 | e;
    }

    public void setDE(int de) {
        d = de >> 8;
        e = de & 0xff;
    }

    public int getHL(){
        return h << 8 | l;
    }

    public void setHL(int hl) {
        h = hl >> 8;
        l = hl & 0xff;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public void setLowerSp(int lower) {
        this.sp = (sp & 0xFF00) | (lower);
    }

    public void setUpperSp(int upper) {
        this.sp = (sp & 0x00FF) | (upper << 8);
    }

    public int getLowerSp(){
        return sp & 0x00FF;
    }

    public int getUpperSp(){
        return sp & 0xFF00;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void incPc(){
        pc++;
    }

    @Override
    public String toString() {
        //TODO
        return "Registers{" +
                "a=0x" + Integer.toHexString(a) +
                ", f=0x" + Integer.toHexString(f) +
                ", b=0x" + Integer.toHexString(b) +
                ", c=0x" + Integer.toHexString(c) +
                ", d=0x" + Integer.toHexString(d) +
                ", e=0x" + Integer.toHexString(e) +
                ", h=0x" + Integer.toHexString(h) +
                ", l=0x" + Integer.toHexString(l) +
                ", sp=0x" + Integer.toHexString(sp) +
                ", pc=0x" + Integer.toHexString(pc) +
                '}';
    }
}
