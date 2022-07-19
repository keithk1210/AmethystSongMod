package net.fabricmc.allseeingeye.items.structureeyes;

import net.fabricmc.allseeingeye.AllSeeingEye;
import net.fabricmc.allseeingeye.movement.PlayerManipulator;
import net.fabricmc.allseeingeye.utils.SearchType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.StructureTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.Structures;

import java.util.List;

public class AncientCityEye extends BaseStructureEye{

    public AncientCityEye(Settings settings) {
        super(settings, null, 30);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        AllSeeingEye.LOGGER.info("[structure]: world.isClient: " + world.isClient);
        if (!world.isClient && super.onUse(playerEntity,hand)) {

            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;
                BlockPos blockPos = null;
                int checks = 0;
                blockPos = serverWorld.getChunkManager().getChunkGenerator().locateStructure(serverWorld, RegistryEntryList.of(Structures.ANCIENT_CITY), playerEntity.getBlockPos(), 100, false).getFirst();
                AllSeeingEye.LOGGER.info("candidate block: " + blockPos + " inhabited time: " + world.getChunk(blockPos).getInhabitedTime());


                if (blockPos == null) {
                    playerEntity.sendMessage(Text.literal("Even the All-Seeing Eye has its limits."));
                    return TypedActionResult.fail(playerEntity.getStackInHand(hand));
                }

                AllSeeingEye.LOGGER.info("[structure]: blockPos != null: " +(blockPos != null));
                playerEntity.sendMessage(Text.literal("Destination: " + blockPos.getX() + ", " + blockPos.getZ()));

                PlayerManipulator.beginProcess(playerEntity,blockPos, SearchType.STRUCTURE);
            }
            return TypedActionResult.success(playerEntity.getStackInHand(hand));
        }
        return TypedActionResult.fail(playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {

        // formatted red text
        tooltip.add(Text.translatable("item.allseeingeye.ancient_city_eye.tooltip").formatted(Formatting.RED));
    }
}
