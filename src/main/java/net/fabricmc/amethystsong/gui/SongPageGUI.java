package net.fabricmc.amethystsong.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.Utils.Note;
import net.fabricmc.amethystsong.songs.Song;
import net.fabricmc.amethystsong.songs.SongManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class SongPageGUI extends LightweightGuiDescription {


    private SongPageManager manager;
    private int page;
    private ArrayList<Note> notes;
    public SongPageGUI(SongPageManager manager,int page,ArrayList notes) {
        this.manager = manager;
        this.page = page;
        this.notes = notes;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);
        Song song = manager.getSong();

        WButton nextButton = new NextButton(Text.literal(">"),manager,this.page+1);
        WButton prevButton = new PrevButton(Text.literal("<"),manager,this.page-1);
        if (this.page - 1 > 0) {
            root.add(prevButton,0,1,1,1);
        }
        if (this.page < manager.getPages().size()) {
            root.add(nextButton,4,1,1,1);
        }

        if (song != null) {
            for (int currentNote = 0; currentNote < song.getNumNotes(); currentNote++) {
                WButton noteButton = new WButton(Text.literal(this.notes.get(currentNote).getName()));
                root.add(noteButton,2,(currentNote + 1) * 2,2,1);
            }
        }


        root.validate(this);



    }

   public void addNote(Note note) {
        this.notes.add(note);
        if (this.notes.size() > SongPageManager.NOTES_PER_PAGE) {
            AmethystSong.LOGGER.info("WARNING!!! Notes on page exceeded " + SongPageManager.NOTES_PER_PAGE);
        }
   }
}

class NextButton extends WButton {
    public NextButton(Text label,SongPageManager manager,int pageToFlipTo) {
        super(label);
        this.setOnClick(() -> {
                    MinecraftClient.getInstance().setScreen(new SongPageScreen(manager.getPages().get(pageToFlipTo)));
        });
    }
}

class PrevButton extends WButton {
    public PrevButton(Text label,SongPageManager manager, int pageToFlipTo) {
        super(label);
        this.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(new SongPageScreen(manager.getPages().get(pageToFlipTo)));
        });
    }
}


