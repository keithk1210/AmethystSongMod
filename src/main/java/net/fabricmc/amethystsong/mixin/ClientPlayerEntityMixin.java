package net.fabricmc.amethystsong.mixin;

import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Environment(EnvType.CLIENT)
@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @ModifyArgs(method = "playSound(Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V"))
    private void method(Args args) {
        AmethystSong.LOGGER.info("in");
        if (args.get(1) instanceof SoundCategory) {
            SoundCategory category = (SoundCategory) args.get(1);
            AmethystSong.LOGGER.info("in");
            if (category.equals(SoundCategory.PLAYERS)) {
                AmethystSong.LOGGER.info("Player Sound Category was: " + category.getName());
            }
        }
    }
}
