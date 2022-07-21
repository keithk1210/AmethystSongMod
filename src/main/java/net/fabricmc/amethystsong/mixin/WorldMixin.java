package net.fabricmc.amethystsong.mixin;


import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.songs.SongManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

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

    @ModifyArgs(method = "playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;playSound(Lnet/minecraft/entity/player/PlayerEntity;DDDLnet/minecraft/sound/SoundEvent;Lnet/minecraft/sound/SoundCategory;FFJ)V"))
    private void method(Args args) {
        /*
        if (args.get(4) instanceof SoundEvent) {
            SoundEvent se = (SoundEvent) args.get(4);
            if (se.getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP.getId()) == 0) {
                if (SongManager.getActiveSong() != null) {
                    AmethystSong.LOGGER.info("inside here");
                    args.set(8,1L);
                    args.set(7,SongManager.getActiveSong().getNote().getPitch());
                }
            }
        }

         */
    }


}
