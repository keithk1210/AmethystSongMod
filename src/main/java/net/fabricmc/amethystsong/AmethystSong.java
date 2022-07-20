package net.fabricmc.amethystsong;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.amethystsong.Utils.Note;
import net.fabricmc.amethystsong.gui.EditorGUI;
import net.fabricmc.amethystsong.gui.EditorScreen;
import net.fabricmc.amethystsong.gui.SongButton;
import net.fabricmc.amethystsong.gui.SongPageManager;
import net.fabricmc.amethystsong.keybindings.KeybindingRegistrationHelper;
import net.fabricmc.amethystsong.songs.Song;
import net.fabricmc.amethystsong.songs.SongManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmethystSong implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "amethystsong";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		KeybindingRegistrationHelper.register();
		ClientTickEvents.END_CLIENT_TICK.register(this::clientTickEvent);
		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
			dispatcher.register(CommandManager.literal("song")
					.then(CommandManager.argument("songNumber", IntegerArgumentType.integer(0, SongManager.getNumSongs() + 177))
						.then(CommandManager.literal("add")
							.then(CommandManager.argument("noteName",StringArgumentType.string())
							.executes(context -> {
								SongManager.getSong(IntegerArgumentType.getInteger(context,"songNumber")).addNote(Note.valueOf(StringArgumentType.getString(context,"noteName").toUpperCase()));
								return 1;
									}
							)))));
			dispatcher.register(CommandManager.literal("song")
					.then(CommandManager.literal("add").executes(context -> {
						Song newSong = new Song();
						SongManager.addSong(newSong);
						int numSongs = SongManager.getNumSongs();
						newSong.setNumSong(numSongs);
						SongPageManager manager = new SongPageManager(newSong);
						newSong.setManager(manager);
						return 1;
					})));
		}));
		LOGGER.info("AmethystSong initialized");
	}

	private void clientTickEvent(MinecraftClient mc) {
		if (KeybindingRegistrationHelper.openMenuKey.wasPressed()) {

			mc.setScreen(new EditorScreen(new EditorGUI()));
		}

	}
}
