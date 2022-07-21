package net.fabricmc.amethystsong.mixin;

import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.songs.SongManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @ModifyArgs(method = "playSound(Lnet/minecraft/sound/SoundEvent;FF)V",at = @At(value = "INVOKE", target ="Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V" ))
    private void method(Args args) {
        //AmethystSong.LOGGER.info("in");
        /*
        if (args.get(5) instanceof SoundCategory) {
            SoundCategory category = (SoundCategory) args.get(5);
            //AmethystSong.LOGGER.info("in");
            if (category.equals(SoundCategory.PLAYERS)) {
                SoundEvent event = (SoundEvent) args.get(4);
                if (event.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP.getId()) == 0) {
                    if (SongManager.getActiveSong() != null) {
                        //args.set(7, SongManager.getActiveSong().getNote().getPitch());
                    }
                }
            }
        }

         */
    }
}
