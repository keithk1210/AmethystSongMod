package net.fabricmc.amethystsong.gui;

import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.songs.Song;

import java.util.ArrayList;

public class SongPageManager {

    private Song song;
    public static int NOTES_PER_PAGE = 4;
    private ArrayList<SongPageGUI> pages;

    public SongPageManager(Song song){
        this.pages = new ArrayList<>();
        this.song = song;
        int numPages = (int) Math.ceil((float)this.song.getNumNotes()/(float)NOTES_PER_PAGE);
        AmethystSong.LOGGER.info("numPages " + numPages);
        for (int currentPage = 0; currentPage < numPages; currentPage++) {
            int start = currentPage * NOTES_PER_PAGE;
            int end = start + NOTES_PER_PAGE;
            this.pages.add(new SongPageGUI(this,currentPage+1,(ArrayList) song.getNotes().subList(start,end)));
            AmethystSong.LOGGER.info("Page added to SPM");
        }

    }

    public void refreshPages() {
        this.pages.clear();
        int numPages = (int) Math.ceil(this.song.getNumNotes()/NOTES_PER_PAGE);
        for (int currentPage = 0; currentPage < numPages; currentPage++) {
            int start = currentPage * NOTES_PER_PAGE;
            int end = start + NOTES_PER_PAGE;
            this.pages.add(new SongPageGUI(this,currentPage+1,(ArrayList) song.getNotes().subList(start,end)));
            AmethystSong.LOGGER.info("Page added to SPM");
        }
    }

    public ArrayList<SongPageGUI> getPages() {
        return pages;
    }

    public Song getSong() {
        return song;
    }
}
