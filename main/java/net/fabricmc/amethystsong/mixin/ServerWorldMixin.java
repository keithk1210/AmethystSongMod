package net.fabricmc.amethystsong.mixin;


import net.fabricmc.amethystsong.AmethystSong;
import net.fabricmc.amethystsong.Utils.Utils;
import net.fabricmc.amethystsong.songs.CMajorScale;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {


    @Shadow @Final private MinecraftServer server;

    @Shadow public abstract ServerWorld toServerWorld();

    @ModifyArg(method = "playSound", at = @At(value = "INVOKE", target = "net/minecraft/server/PlayerManager.sendToAround (Lnet/minecraft/entity/player/PlayerEntity;DDDDLnet/minecraft/util/registry/RegistryKey;Lnet/minecraft/network/Packet;)V"),index = 6)
    private Packet print(Packet<?> packet) {
        /*
        AmethystSong.LOGGER.info("inside modify args method");
        PlaySoundS2CPacket s2CPacket = null;
        if (packet instanceof PlaySoundS2CPacket) {
            s2CPacket = (PlaySoundS2CPacket) packet;
        }

        if (Utils.isAmethystEvent(s2CPacket.getSound())) {
                AmethystSong.LOGGER.info("sound: " + s2CPacket.getSound().toString() + " id: " + s2CPacket.getSound().getId().toString() + " path: " + s2CPacket.getSound().getId().getPath() + " namespace: " + s2CPacket.getSound().getId().getNamespace());
                AmethystSong.LOGGER.info("category: " + s2CPacket.getCategory().getName());
                AmethystSong.LOGGER.info("volume: " + s2CPacket.getVolume());
                AmethystSong.LOGGER.info("pitch: " + s2CPacket.getPitch());
                AmethystSong.LOGGER.info("seed: " + s2CPacket.getSeed());

                AmethystSong.LOGGER.info("---");
                return new PlaySoundS2CPacket(SoundEvents.BLOCK_BREWING_STAND_BREW, s2CPacket.getCategory(), s2CPacket.getX(), s2CPacket.getY(), s2CPacket.getZ(), s2CPacket.getVolume(), CMajorScale.getNote().getPitch(), s2CPacket.getSeed());
        } else {
                AmethystSong.LOGGER.info("---");
                return packet;
        }

         */
        return packet;
    }


}
