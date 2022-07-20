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
    private int pageAsIndex;
    private ArrayList<Note> notes;

    private static int NOTES_PER_ROW = 3 ;
    private static int NOTES_PER_COLUMN = 4;
    public SongPageGUI(SongPageManager manager,int page,ArrayList notes) {
        this.manager = manager;
        this.page = page;
        this.pageAsIndex = page-1;
        this.notes = notes;
        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(256, 240);
        root.setInsets(Insets.ROOT_PANEL);
        Song song = manager.getSong();
        AmethystSong.LOGGER.info("Song # " + this.manager.getSong().getNumSong() + " this.manager.pages.size() " + this.manager.getPages().size() + " this.page " + this.page);
        WButton nextButton = new NextButton(Text.literal(">"),manager,this.pageAsIndex+1);
        WButton prevButton = new PrevButton(Text.literal("<"),manager,this.pageAsIndex-1);
        if (this.pageAsIndex > 0) {
            root.add(prevButton,0,1,1,1);
        }
        if (song.getNotes().size() - (this.page * SongPageManager.NOTES_PER_PAGE) > 0) {
            root.add(nextButton,12,1,1,1);
        }

        if (song != null) {
            for (int currentColumn = 0; currentColumn < NOTES_PER_COLUMN; currentColumn++) {
                for (int currentRow = 0; currentRow < NOTES_PER_ROW; currentRow++) {
                    int currentNote = currentColumn * NOTES_PER_COLUMN + currentRow;
                    try {
                        if (currentNote < this.notes.size()) {
                            WButton noteButton = new WButton(Text.literal(this.notes.get(currentNote).getName() + " (" + (currentNote + 1) + "/" + this.manager.getSong().getNumNotes() + ")"));
                            root.add(noteButton, (currentRow * 4) + 1, (currentColumn * 2) + 1, 3, 1);
                            AmethystSong.LOGGER.info(currentRow + ", " + currentColumn);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        AmethystSong.LOGGER.info(e.toString());
                    }
                }
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
            try{
                MinecraftClient.getInstance().setScreen(new SongPageScreen(manager.getPages().get(pageToFlipTo)));
            }  catch (IndexOutOfBoundsException e) {
                AmethystSong.LOGGER.info(e.toString());
            }

        });
    }
}

class PrevButton extends WButton {
    public PrevButton(Text label,SongPageManager manager, int pageToFlipTo) {
        super(label);
        this.setOnClick(() -> {
            try{
                MinecraftClient.getInstance().setScreen(new SongPageScreen(manager.getPages().get(pageToFlipTo)));
            }  catch (IndexOutOfBoundsException e) {
                AmethystSong.LOGGER.info(e.toString());
            }
        });
    }
}


