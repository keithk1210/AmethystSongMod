package net.fabricmc.amethystsong.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.amethystsong.Utils.Note;
import net.fabricmc.amethystsong.songs.Song;
import net.fabricmc.amethystsong.songs.SongManager;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;

public class SongGUI extends LightweightGuiDescription {

    private int numSong;
    public SongGUI(int numSong) {
        this.numSong = numSong;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);
        Song song = SongManager.getSong(this.numSong);

        BiConsumer<Note, PortalDestination> configurator = (Note note, PortalDestination destination) -> {
            destination.button.setLabel(Text.literal(note.getName()));
        };

        if (song != null) {
            WListPanel<Note,PortalDestination> list = new WListPanel<>(song.getNotes(),PortalDestination::new,configurator);
            list.setListItemHeight(18);
            root.add(list, 5, 2, 7, 12);

            WButton newNoteButton = new WButton(Text.translatable("gui.amethystsong.newnotebutton"));
            newNoteButton.setOnClick(() -> {

                Note note = Note.getRandom();
                song.addNote(note);
                root.remove(list);
                WListPanel<Note,PortalDestination> newList = new WListPanel<>(song.getNotes(),PortalDestination::new,configurator);
                newList.setListItemHeight(18);
                root.add(newList, 5, 2, 7, 12);
            });
            root.add(newNoteButton, 0, 2, 4, 1);



        }


        root.validate(this);



    }

    public static class PortalDestination extends WPlainPanel {
        WButton button;
        public PortalDestination() {
            button = new WButton(Text.literal("foo"));
            this.add(button,18+4,2,3*18,9);
            this.setSize(7*18,2*18);
        }
    }
}


