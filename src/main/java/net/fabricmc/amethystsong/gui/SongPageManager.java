package net.fabricmc.amethystsong.gui;

import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.songs.Song;

import java.util.ArrayList;

public class SongPageManager {

    private Song song;
    public final static int NOTES_PER_PAGE = 12;
    private ArrayList<SongPageGUI> pages;

    public SongPageManager(Song song){
        this.pages = new ArrayList<>();
        this.song = song;
        /*
        int numPages = (int) Math.ceil((float)this.song.getNumNotes()/(float)NOTES_PER_PAGE);
        AmethystSong.LOGGER.info("numPages " + numPages);
        for (int currentPage = 0; currentPage < numPages; currentPage++) {
            int start = currentPage * NOTES_PER_PAGE;
            int end = start + NOTES_PER_PAGE;
            this.pages.add(new SongPageGUI(this,currentPage+1,(ArrayList) song.getNotes().subList(start,end)));
            AmethystSong.LOGGER.info("Page added to SPM");
        }
         */

    }

    public void refreshPages() {
        this.pages.clear();
        double preCeil = (double)this.song.getNumNotes()/(double)NOTES_PER_PAGE;
        AmethystSong.LOGGER.info("preCEil " + preCeil);
        int numPages = (int) Math.ceil(preCeil);
        AmethystSong.LOGGER.info("NUm pages: " + numPages);
        for (int currentPage = 0; currentPage < numPages; currentPage++) {
            int start = currentPage * NOTES_PER_PAGE;
            int end = start + NOTES_PER_PAGE;
            if (end > song.getNotes().size()) {
                end = song.getNotes().size();
                AmethystSong.LOGGER.info("end was capped");
            }
            try {
                this.pages.add(new SongPageGUI(this,currentPage+1,new ArrayList(this.song.getNotes().subList(start,end))));
                AmethystSong.LOGGER.info("Page added to SPM");
            } catch(Exception e) {
                e.printStackTrace();
                AmethystSong.LOGGER.info(e.toString());
            }


        }
    }

    public ArrayList<SongPageGUI> getPages() {
        return pages;
    }

    public Song getSong() {
        return song;
    }
}
