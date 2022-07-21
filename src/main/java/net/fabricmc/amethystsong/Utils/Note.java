package net.fabricmc.amethystsong.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Note {
    C0(.5f,"C0"),
    CSHARP0(277.183f/523.3f,"C#0"),
    DFLAT0(277.183f/523.3f,"Db0"),
    D0(293.665f/523.f,"D0"),
    DSHARP0(311.127f/523.3f,"D#0"),
    EFLAT0(311.127f/523.3f,"Eb0"),
    E0(329.628f/523.3f,"E0"),
    F0(349.228f/523.3f,"F0"),
    FSHARP0(369.994f/523.3f,"F#0"),
    G0(391.995f/523.3f,"G0"),
    GSHARP0(415.305f/523.3f,"G#0"),
    AFLAT0(415.305f/523.3f,"Ab0"),
    A0(440.0f/523.3f,"A"),
    ASHARP0(466.164f/523.3f,"A#0"),
    BFLAT0(466.164f/523.3f,"Bb0"),
    B0(493.883f/523.3f,"B0"),
    C1(523.3f/523.3f,"C1"),
    CSHARP1(554.37f/523.3f,"C#1"),
    DFLAT1(554.37f/523.3f,"Db1"),
    D1(587.3f/523.3f,"D1"),
    DSHARP1(622.25f/523.3f,"D#1"),
    EFLAT1(622.25f/523.3f,"Eb1"),
    E1(659.3f/523.3f,"E1"),
    F1(698.5f/523.3f,"F1"),
    FSHARP1(739.99f/523.3f,"F#1"),
    GFLAT1(739.99f/523.3f,"Gb1"),
    G1(784.0f/523.3f,"G"),
    GSHARP1(830.61f/523.3f,"G#1"),
    AFLAT1(830.61f/523.3f,"Ab1"),
    A1(880.0f/523.3f,"A1"),
    ASHARP1(932.33f/523.3f,"A#1"),
    BFLAT1(932.33f/523.3f,"Bb1"),
    B1(987.8f/523.3f,"B1"),
    C2(2.0f,"C2");



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