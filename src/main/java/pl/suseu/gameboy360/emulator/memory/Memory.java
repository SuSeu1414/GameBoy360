package pl.suseu.gameboy360.emulator.memory;


//Changed my mind. This class will probably be unused
public class Memory {

    private String id = "";
    private int start;
    private int end;
    private int[] mem;

    public Memory(int start, int end) {
        this(start, end, "");
    }

    public Memory(int start, int end, String id) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.mem = new int[end-start+1];
    }

    public Memory(int start, int end, String id, int[] mem) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.mem = mem;
    }

    public boolean isAddressIn(int address) {
        return address >= start && address <= end;
    }

    public int getValue(int address) {
        return mem[address - start];
    }

    public void setValue(int address, int value) {
        mem[address - start] = value;
    }

    public String getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int[] getMem() {
        return mem;
    }
}
