package net.fabricmc.amethystsong.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.songs.Song;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SongButton extends WButton {

    private Song song;
    public SongButton(Text label, Song song) {
        super(label);
        this.song = song;

        this.setOnClick(() -> {
            this.song.getManager().refreshPages();
            if (this.song.getManager().getPages().size() > 0) {
                MinecraftClient.getInstance().setScreen(new SongPageScreen(this.song.getManager().getPages().get(0)));
            } else {
                MinecraftClient.getInstance().player.sendMessage(Text.literal("Try using the /" + AmethystSong.MOD_ID + " get {songNumber} add {noteName} command!"));
                AmethystSong.LOGGER.info("ERROR! pages was empty!");
            }

        });
    }


}
