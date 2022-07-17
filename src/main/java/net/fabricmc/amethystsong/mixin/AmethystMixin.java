package net.fabricmc.amethystsong.mixin;

import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AmethystBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin (AmethystBlock.class)
public class AmethystMixin {

    @Inject(method = "onProjectileHit", at = @At("HEAD"))
    private void print(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile, CallbackInfo info) {
       // AmethystSong.LOGGER.info("projectile hit");
    }

}
