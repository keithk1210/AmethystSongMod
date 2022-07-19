package net.fabricmc.amethystsong.songs;

import net.fabricmc.amethystsong.Utils.Note;

import java.util.ArrayList;

public class Song {

    private int currentNoteIndex = 0;
    private ArrayList<Note> notes;

    public Song() {
        notes = new ArrayList<>();
    }

    private int getCurrentNoteIndex() {
        return currentNoteIndex;
    }

    private void nextNote() {
        if (currentNoteIndex + 1 < notes.size()) {
            currentNoteIndex += 1;
        } else {
            currentNoteIndex = 0;
        }
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public Note getNote() {
        //AmethystSong.LOGGER.info("get note called (head). current note: " + currentNote);
        int currentNote = getCurrentNoteIndex();
        Note returnVal = null;
        if (currentNote < notes.size()) {
            returnVal = notes.get(currentNote);
        }
        nextNote();
        if (returnVal != null ){
            return returnVal;
        } else {
            return null;
        }

    }

    public int getNumNotes() {
        return notes.size();
    }

    public void addNote(Note note) {
        notes.add(note);
    }
}
