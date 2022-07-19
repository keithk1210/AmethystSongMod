package net.fabricmc.allseeingeye.items.blockeyes;

import net.fabricmc.allseeingeye.AllSeeingEye;
import net.fabricmc.allseeingeye.items.BaseEye;
import net.fabricmc.allseeingeye.movement.PlayerManipulator;
import net.fabricmc.allseeingeye.scanning.ChunkScanner;
import net.fabricmc.allseeingeye.utils.ProcessType;
import net.fabricmc.allseeingeye.utils.SearchType;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.ClientAdvancementManager;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.List;

public class BaseBlockEye extends BaseEye {
    HashSet<Block> targets;
    private int minDepth;

    public BaseBlockEye(Settings settings,HashSet<Block> targets, int levelsRequired, int minDepth) {
        super(settings,levelsRequired);
        this.targets = targets;
        this.minDepth = minDepth;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        AllSeeingEye.LOGGER.info("[block]: world.isClient: " + world.isClient);

        if (world.isClient) {
            if (playerEntity instanceof ClientPlayerEntity) {
                AllSeeingEye.LOGGER.info("was clientplayer entity");
            }
            playerEntity.setPos(playerEntity.getBlockX() + .5, playerEntity.getBlockY(),playerEntity.getBlockZ() + .5);
        }
        if (!world.isClient && super.onUse(playerEntity, hand)) {
            BlockPos foundBlock = ChunkScanner.circleScan(playerEntity, targets, 101);
            AllSeeingEye.LOGGER.info("[block]: foundBlock != null: " +(foundBlock != null));
            if (foundBlock != null) {
                PlayerManipulator.beginProcess(playerEntity, foundBlock, SearchType.BLOCK);
            }
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        }
        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }

    public int getMinDepth() {
        return minDepth;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        // formatted red text
        tooltip.add(Text.translatable("item.allseeingeye.block_eye.tooltip1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.allseeingeye.block_eye.tooltip2").formatted(Formatting.GRAY));
    }

}
