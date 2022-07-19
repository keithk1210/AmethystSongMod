package net.fabricmc.amethystsong.gui;

import io.github.cottonmc.cotton.gui.widget.WButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class SongButton extends WButton {

    private int numSong;
    public SongButton(Text label,int numSong) {
        super(label);
        this.numSong = numSong;
        this.setOnClick(() -> {
            MinecraftClient.getInstance().setScreen(new SongScreen(new SongGUI(this.numSong)));
        });
    }


}
