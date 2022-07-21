package net.fabricmc.amethystsong.songs;

import net.fabricmc.amethystsong.Utils.Note;

public class CMajorScale {

    private static int currentNote = 0;

    public static final Note[] notes = new Note[] {
            /*
            Note.C,
            Note.D,
            Note.E,
            Note.F,
            Note.G,
            Note.A,
            Note.B
             */
    };

    private static int getCurrentNote() {
        return currentNote;
    }

    public static void setCurrentNote(int currentNote) {
        CMajorScale.currentNote = currentNote;
    }

    private static void nextNote() {
        //AmethystSong.LOGGER.info("next note called (head). current note: " + currentNote);
        setCurrentNote(currentNote + 1);
        if (getCurrentNote() > notes.length - 1) {
            setCurrentNote(0);
        }
        //AmethystSong.LOGGER.info("next note called (foot). current note: " + currentNote);
    }

    public static Note getNote() {
        //AmethystSong.LOGGER.info("get note called (head). current note: " + currentNote);
        int currentNote = getCurrentNote();
        Note returnVal = null;
        if (currentNote < notes.length) {
            returnVal = notes[currentNote];
        }
        nextNote();
        if (returnVal != null ){
            return returnVal;
        } else {
            return null;
        }

    }

}
