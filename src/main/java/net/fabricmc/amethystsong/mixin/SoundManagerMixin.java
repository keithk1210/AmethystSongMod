package net.fabricmc.amethystsong.mixin;


import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.checkerframework.checker.units.qual.Mass;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Environment(EnvType.CLIENT)
@Mixin(SoundManager.class)
public abstract class SoundManagerMixin {


    @Shadow
    @Final
    private Map<Identifier, WeightedSoundSet> sounds;

    @Shadow public abstract void play(SoundInstance sound);

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"))
    private void print(SoundInstance sound, CallbackInfo info) {

        //AmethystSong.LOGGER.info("play(soundinstance) " + sound);
    }
}
