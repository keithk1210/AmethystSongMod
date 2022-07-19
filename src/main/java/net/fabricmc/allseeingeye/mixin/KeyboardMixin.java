package net.fabricmc.allseeingeye.mixin;

import net.fabricmc.allseeingeye.movement.CardinalDirection;
import net.fabricmc.allseeingeye.utils.ProcessType;
import net.fabricmc.allseeingeye.utils.SearchType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.allseeingeye.AllSeeingEye;
import net.fabricmc.allseeingeye.movement.MovementDirection;
import net.fabricmc.allseeingeye.movement.PlayerManipulator;
import net.minecraft.client.input.KeyboardInput;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(KeyboardInput.class)
public class KeyboardMixin {

	@Redirect(method = "tick", at = @At(
			value = "FIELD",
			target = "net/minecraft/client/input/KeyboardInput.pressingForward:Z",
			opcode = Opcodes.GETFIELD,
			ordinal = 0))
	private boolean onPressingForward(KeyboardInput input) {
		input.pressingForward = input.pressingForward || PlayerManipulator.hasDirection(MovementDirection.FORWARD);
		//double angle = PlayerManipulator.getHeadMovements().peek().getDestinationAngle();
		/*
		if (PlayerManipulator.getCurrentSearchType() == SearchType.BLOCK) {
			//checkIfBlockBelow(Blocks.LAVA.getDefaultState(),Blocks.OBSIDIAN.getDefaultState());
			//checkIfBlockBelow(Blocks.WATER.getDefaultState(),Blocks.PACKED_ICE.getDefaultState());
			if (Double.compare(angle, CardinalDirection.EAST.getDegrees()) == 0 || Double.compare(angle, CardinalDirection.WEST.getDegrees()) == 0) {
				//for block search
				if (Math.abs(PlayerManipulator.getPlayer().getX() - (PlayerManipulator.getDestination().getX() + .5)) <= .1) {
					PlayerManipulator.getPlayer().setPos(PlayerManipulator.getPlayer().getBlockX() + .5, PlayerManipulator.getPlayer().getBlockY(), PlayerManipulator.getPlayer().getBlockZ() + .5);
					PlayerManipulator.clearToggled();
					PlayerManipulator.getHeadMovements().remove();
					//PlayerManipulator.log("Toggled was cleared");
					PlayerManipulator.printHeadMovements();
					if (PlayerManipulator.getHeadMovements().size() > 0) {
						PlayerManipulator.setCurrentProcess(ProcessType.ANGULAR_YAW);
					} else {
						PlayerManipulator.setCurrentProcess(ProcessType.ANGULAR_PITCH);
						//AllSeeingEye.LOGGER.info("Horizontal position reached. Settings process to: " + currentProcess);
					}
					//for structure search
				}
			} else if (Double.compare(angle, CardinalDirection.NORTH.getDegrees()) == 0 || Double.compare(angle, CardinalDirection.SOUTH.getDegrees()) == 0) {
				if (Math.abs(PlayerManipulator.getPlayer().getZ() - (PlayerManipulator.getDestination().getZ() + .5)) <= .1) {
					PlayerManipulator.getPlayer().setPos(PlayerManipulator.getPlayer().getBlockX() + .5, PlayerManipulator.getPlayer().getBlockY(), PlayerManipulator.getPlayer().getBlockZ() + .5);
					PlayerManipulator.clearToggled();
					PlayerManipulator.getHeadMovements().remove();
					//log("Toggled was cleared");
					//printHeadMovements();
					if (PlayerManipulator.getHeadMovements().size() > 0) {
						PlayerManipulator.setCurrentProcess(ProcessType.ANGULAR_YAW);
					} else {
						PlayerManipulator.setCurrentProcess(ProcessType.ANGULAR_PITCH);
						//AllSeeingEye.LOGGER.info("Horizontal position reached. Settings process to: " + currentProcess);
					}
				}
			}
		}

		 */
		if (PlayerManipulator.getCurrentProcess() != null && PlayerManipulator.getCurrentProcess() != ProcessType.HORIZONTAL) {
			return false;
		}
		return input.pressingForward;
	}

	@Redirect(method = "tick", at = @At(
			value = "FIELD",
			target = "net/minecraft/client/input/KeyboardInput.pressingBack:Z",
			opcode = Opcodes.GETFIELD,
			ordinal = 0))
	private boolean onPressingBack(KeyboardInput input) {
		input.pressingBack = input.pressingBack;
		if (PlayerManipulator.getCurrentProcess() != null) {
			return false;
		}
		return !PlayerManipulator.hasDirection(MovementDirection.FORWARD) && input.pressingBack ;
	}

	@Redirect(method = "tick", at = @At(
			value = "FIELD",
			target = "net/minecraft/client/input/KeyboardInput.pressingLeft:Z",
			opcode = Opcodes.GETFIELD,
			ordinal = 0))
	private boolean onPressingLeft(KeyboardInput input) {
		input.pressingLeft = input.pressingLeft;
		if (PlayerManipulator.getCurrentProcess() != null) {
			return false;
		}
		return !PlayerManipulator.hasDirection(MovementDirection.FORWARD) && input.pressingLeft;
	}

	@Redirect(method = "tick", at = @At(
			value = "FIELD",
			target = "net/minecraft/client/input/KeyboardInput.pressingRight:Z",
			opcode = Opcodes.GETFIELD,
			ordinal = 0))
	private boolean onPressingRight(KeyboardInput input) {
		input.pressingRight = input.pressingRight;
		if (PlayerManipulator.getCurrentProcess() != null) {
			return false;
		}
		return !PlayerManipulator.hasDirection(MovementDirection.FORWARD)&& input.pressingRight;
	}

}