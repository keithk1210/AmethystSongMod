package net.fabricmc.amethystsong.mixin;


import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.Utils.Note;
import net.fabricmc.amethystsong.Utils.Utils;
import net.fabricmc.amethystsong.songs.CMajorScale;
import net.fabricmc.amethystsong.songs.SongManager;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.Sound;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import org.checkerframework.checker.units.qual.A;
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
        /*
        SoundEvent se = null;
        if (args.get(3) instanceof SoundEvent) {
            se = args.get(3);
            AmethystSong.LOGGER.info(se.getId().toString());
            if (se.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE.getId()) == 0) {
                if (SongManager.getActiveSong() != null) {
                    Note currentNote = SongManager.getActiveSong().getNote();
                    if (currentNote != null) {
                        AmethystSong.LOGGER.info(currentNote.toString());
                        args.set(8, 1L); //set the seed to be the same each time
                        args.set(5, 1.0F); //set to max volume
                        args.set(6, currentNote.getPitch());
                    }
                }
            }
            if (se.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP.getId()) == 0) {
                AmethystSong.LOGGER.info("amethyst step event on clientworld");
            }
        }

         */


    }

    @ModifyArgs(method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZJ)V", at = @At(
            value = "INVOKE", target = "Lnet/minecraft/client/sound/SoundManager;play(Lnet/minecraft/client/sound/SoundInstance;)V"))
    private void anothermethod(Args args) {
        try {
            PositionedSoundInstance positionedSoundInstance = args.get(0);
            if (positionedSoundInstance.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP.getId()) == 0) {
                AmethystSong.LOGGER.info(positionedSoundInstance.getId().toString());
                if (SongManager.getActiveSong() != null) {
                    args.set(0,new PositionedSoundInstance(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP,SoundCategory.PLAYERS,1.0F,SongManager.getActiveSong().getNote().getPitch(), Random.create(1L),positionedSoundInstance.getX(),positionedSoundInstance.getY(),positionedSoundInstance.getZ()));
                }
            }
            if (positionedSoundInstance.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE.getId()) == 0) {
                AmethystSong.LOGGER.info(positionedSoundInstance.getId().toString());
                if (SongManager.getActiveSong() != null) {
                    args.set(0,new PositionedSoundInstance(SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE,SoundCategory.PLAYERS,1.0F,SongManager.getActiveSong().getNote().getPitch(), Random.create(1L),positionedSoundInstance.getX(),positionedSoundInstance.getY(),positionedSoundInstance.getZ()));
                }
            }
            if (positionedSoundInstance.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK.getId()) == 0) {
                AmethystSong.LOGGER.info(positionedSoundInstance.getId().toString());
                if (SongManager.getActiveSong() != null) {
                    args.set(0,new PositionedSoundInstance(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK,SoundCategory.PLAYERS,1.0F,SongManager.getActiveSong().getNote().getPitch(), Random.create(1L),positionedSoundInstance.getX(),positionedSoundInstance.getY(),positionedSoundInstance.getZ()));
                }
            }
        } catch (NullPointerException e) {
            AmethystSong.LOGGER.info(e.toString());
        }

    }




}
