package net.fabricmc.amethystsong.mixin;


import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.Utils.Note;
import net.fabricmc.amethystsong.Utils.Utils;
import net.fabricmc.amethystsong.songs.CMajorScale;
import net.fabricmc.amethystsong.songs.SongManager;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @ModifyArgs(method = "playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFJ)V", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/client/world/ClientWorld;playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V"))
    private void method(Args args) {
        SoundEvent se = null;
        if (args.get(3) instanceof SoundEvent) {
            se = args.get(3);
            if (se.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE.getId()) == 0) {
                Note currentNote = SongManager.getActiveSong().getNote();
                if (currentNote != null) {
                    AmethystSong.LOGGER.info(currentNote.toString());
                    args.set(8,1L); //set the seed to be the same each time
                    args.set(5,1.0F); //set to max volume
                    args.set(6, currentNote.getPitch());
                }
            }
        }
    }
}
