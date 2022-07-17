package net.fabricmc.amethystsong.mixin;


import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin {

    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Shadow public abstract boolean isClient();

    @Inject(method = "playSound(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", at = @At("HEAD"))
    private void injectMethod(@Nullable PlayerEntity player, BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch, CallbackInfo info) {
        //AmethystSong.LOGGER.info("mixin called not inside");
    }

    @Inject(method = "playSound(DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFZ)V", at = @At("HEAD"))
    private void injectMethod(double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch, boolean useDistance,CallbackInfo info) {
        //AmethystSong.LOGGER.info("inside this one ");
    }


}
