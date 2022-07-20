package net.fabricmc.amethystsong.songs;

import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.Utils.Note;
import net.fabricmc.amethystsong.gui.SongPageManager;

import java.util.ArrayList;

public class Song {

    private int currentNoteIndex = 0;
    private ArrayList<Note> notes;
    private int numSong;
    private SongPageManager manager;

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
        for (Note note: notes) {
            AmethystSong.LOGGER.info("Note: " + note.getName());
        }
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
        AmethystSong.LOGGER.info("Was " + note.getName() + " added to notes? " + notes.contains(note));
    }

    public void setNumSong(int numSong) {
        this.numSong = numSong;
    }

    public int getNumSong() {
        return numSong;
    }

    public SongPageManager getManager() {
        return manager;
    }

    public void setManager(SongPageManager manager) {
        this.manager = manager;
    }
}
