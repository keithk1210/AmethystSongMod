package net.fabricmc.amethystsong.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Note {


    /*

    F_SHARP((float)Math.pow(2,-11/12.0),"F#"),
    G_FLAT((float)Math.pow(2,-11/12.0),"Gb"),
    G((float)Math.pow(2, -10/12.0),"G"),
    G_SHARP((float)Math.pow(2, -9/12.0),"G#"),
    A_FLAT((float)Math.pow(2,-9/12.0),"Ab"),
    A((float)Math.pow(2, -8/12.0),"A"),
    B_FLAT((float)Math.pow(2,-7/12.0),"Bb"),
    C((float)Math.pow(2,-6/12.0),"C"),
    C_SHARP((float)Math.pow(2, -5/12.0),"C#"),
    D((float)Math.pow(2, -4/12.0),"D"),
    D_SHARP((float)Math.pow(2, -3/12.0),"D#"),
    E((float)Math.pow(2, -2/12.0),"E"),
    F((float)Math.pow(2,-1/12.0),"F");
    */
    C(523.3f/523.3f,"C"),
    D(587.3f/523.3f,"D"),
    E(659.3f/523.3f,"E"),
    F(698.5f/523.3f,"F"),
    G(784.0f/523.3f,"G"),
    A(880.0f/523.3f,"A"),
    B(987.8f/523.3f,"B");



    private final float pitch;
    private final String name;
    private static final List<Note> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random random = new Random();


    Note(float pitch,String name) {
        this.pitch = pitch;
        this.name = name;
    }

    public float getPitch() {
        return this.pitch;
    }

    public String getName() {
        return name;
    }

    public static Note getRandom() {
        return VALUES.get(random.nextInt(SIZE));
    }

    @Override
    public String toString() {
        return "Name: " + this.name + " Pitch: " + pitch;
    }

}