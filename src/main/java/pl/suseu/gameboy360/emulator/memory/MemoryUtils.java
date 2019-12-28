package pl.suseu.gameboy360.emulator.memory;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class MemoryUtils {

    public static int[] loadBinary(String filename) throws Exception {
        System.out.println("Loading " + filename + " ...");
        DataInputStream in = new DataInputStream(new FileInputStream(filename));
        byte[] bytes = new byte[in.available()];
        int loadedBytes = in.read(bytes);
        System.out.println("Loaded " + loadedBytes + " bytes.");

        int[] ints = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            ints[i] = bytes[i] & 0xFF;
        }
        return ints;
    }
}
