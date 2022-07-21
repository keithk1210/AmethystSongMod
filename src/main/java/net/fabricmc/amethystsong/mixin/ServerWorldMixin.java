package net.fabricmc.amethystsong.mixin;


import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.Utils.Utils;
import net.fabricmc.amethystsong.songs.CMajorScale;
import net.fabricmc.amethystsong.songs.SongManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {


    @Shadow @Final private MinecraftServer server;

    @Shadow public abstract ServerWorld toServerWorld();

    @ModifyArgs(method = "playSound", at = @At(value = "INVOKE", target = "net/minecraft/server/PlayerManager.sendToAround (Lnet/minecraft/entity/player/PlayerEntity;DDDDLnet/minecraft/util/registry/RegistryKey;Lnet/minecraft/network/Packet;)V"))
    private void print(Args args) {
        /*
        //AmethystSong.LOGGER.info("inside modify args method");
        PlaySoundS2CPacket s2CPacket = null;
        if (args.get(6) instanceof PlaySoundS2CPacket) {
            s2CPacket = (PlaySoundS2CPacket) args.get(6);
        }

        if (s2CPacket.getSound().getId().compareTo(SoundEvents.BLOCK_AMETHYST_BLOCK_STEP.getId()) == 0) {
            //AmethystSong.LOGGER.info("sound: " + s2CPacket.getSound().toString() + " id: " + s2CPacket.getSound().getId().toString() + " path: " + s2CPacket.getSound().getId().getPath() + " namespace: " + s2CPacket.getSound().getId().getNamespace());
           // AmethystSong.LOGGER.info("category: " + s2CPacket.getCategory().getName());
           // AmethystSong.LOGGER.info("volume: " + s2CPacket.getVolume());
            //AmethystSong.LOGGER.info("pitch: " + s2CPacket.getPitch());
            //AmethystSong.LOGGER.info("seed: " + s2CPacket.getSeed());


            //AmethystSong.LOGGER.info("---");
            if (SongManager.getActiveSong() != null) {
                args.set(6,new PlaySoundS2CPacket(s2CPacket.getSound(), s2CPacket.getCategory(), s2CPacket.getX(), s2CPacket.getY(), s2CPacket.getZ(), s2CPacket.getVolume(), SongManager.getActiveSong().getNote().getPitch(), s2CPacket.getSeed()));
                args.set(0,null);
            }
        } else {
            //AmethystSong.LOGGER.info("---");
        }

         */
    }


}
