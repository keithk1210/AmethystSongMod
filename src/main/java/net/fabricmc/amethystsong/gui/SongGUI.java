package net.fabricmc.amethystsong.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.amethystsong.Utils.Note;
import net.fabricmc.amethystsong.songs.Song;
import net.fabricmc.amethystsong.songs.SongManager;
import net.minecraft.text.Text;

public class SongGUI extends LightweightGuiDescription {

    public SongGUI(int numSong) {
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);


        WButton newNoteButton = new WButton(Text.translatable("gui.amethystsong.newnotebutton"));
        newNoteButton.setOnClick(() -> {
            Song song = SongManager.getSong(numSong);
            if (song !=null) {
                Note note = Note.getRandom();
                song.addNote(note);
                WButton noteButton = new WButton(Text.literal(note.getName()));
                root.add(noteButton,5,2 * song.getNumNotes() ,4,1);
            }
        });
        root.add(newNoteButton, 0, 2, 4, 1);


        root.validate(this);
    }
}
