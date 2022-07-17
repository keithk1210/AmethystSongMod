package net.fabricmc.amethystsong.songs;

import java.util.ArrayList;

public class SongManager {

    private static int activeSongIndex = 0;
    private static ArrayList<Song> songs = new ArrayList<>();



    public static int getNumSongs() {
        return songs.size();
    }

    public static Song getSong(int num) {
        if (num < songs.size()) {
            return songs.get(num);
        }
        return null;

    }

    public static void addSong(Song song) {
        songs.add(song);
    }

    public static int getActiveSongNumber() {
        return activeSongIndex + 1;
    }

    public static void nextSong() {
        if (activeSongIndex + 1 < songs.size()) {
            activeSongIndex += 1;
        } else {
            activeSongIndex = 0;
        }

    }

    public static Song getActiveSong() {
        if (activeSongIndex < songs.size()) {
            return songs.get(activeSongIndex);
        } else {
            return null;
        }
    }
}
