package pl.suseu.gameboy360.util;

import java.io.DataInputStream;
import java.io.FileInputStream;

public class Utils {

    private byte[] loadBinary(String filename) throws Exception {
        System.out.println("Loading " + filename + " ...");
        DataInputStream in = new DataInputStream(new FileInputStream(filename));
        byte[] bytes = new byte[in.available()];
        int loadedBytes = in.read(bytes);
        System.out.println("Loaded " + loadedBytes + " bytes.");
        return bytes;
    }

}
