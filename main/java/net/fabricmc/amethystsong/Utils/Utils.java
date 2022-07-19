package net.fabricmc.amethystsong.Utils;

import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class Utils {

    public static boolean isAmethystEvent(SoundEvent e) {
        return e.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK.getId()) == 0
                || e.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_HIT.getId()) == 0 //hit
                || e.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE.getId()) == 0 //place
                || e.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP.getId()) == 0
                || e.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_FALL.getId()) == 0;
    }
}
