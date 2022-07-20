package net.fabricmc.amethystsong.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.amethystsong.songs.Song;
import net.fabricmc.amethystsong.songs.SongManager;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class EditorGUI extends LightweightGuiDescription {

    public EditorGUI() {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);

        WButton activeSongButton = new WButton(Text.literal("Active Song: " + SongManager.getActiveSongNumber()));
        activeSongButton.setOnClick(() -> {
            SongManager.nextSong();
            activeSongButton.setLabel(Text.literal("Active Song: " + SongManager.getActiveSongNumber()));
        });
        root.add(activeSongButton,0,2,4,1);


        WButton newSongButton = new WButton(Text.translatable("gui.amethystsong.newsongbutton"));
        newSongButton.setOnClick(() -> {
            Song newSong = new Song();
            SongManager.addSong(newSong);
            int numSongs = SongManager.getNumSongs();
            newSong.setNumSong(numSongs);
            SongPageManager manager = new SongPageManager(newSong);
            newSong.setManager(manager);
            SongButton songButton = new SongButton(Text.literal("Song " + numSongs),newSong);
            root.add(songButton,5,2 * numSongs,4,1);
        });
        //root.add(newSongButton, 0, 4, 4, 1);

        for (int currentSong = 1; currentSong <= SongManager.getNumSongs(); currentSong++) {
            Song song = SongManager.getSong(currentSong);
            SongButton existingSongButton = new SongButton(Text.literal("Song " + currentSong),song);
            root.add(existingSongButton,5,2 * currentSong,4,1);
        }


        root.validate(this);
    }
}
