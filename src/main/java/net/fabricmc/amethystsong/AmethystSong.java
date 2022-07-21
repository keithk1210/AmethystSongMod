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
import net.minecraft.util.Formatting;
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
			//add note command
			dispatcher.register(CommandManager.literal(MOD_ID)
					.then(CommandManager.literal("get")
						.then(CommandManager.argument("songNumber", IntegerArgumentType.integer(0))
							.then(CommandManager.literal("add")
								.then(CommandManager.argument("noteName",StringArgumentType.string())
								.executes(context -> {
									AmethystSong.LOGGER.info("input was: " + StringArgumentType.getString(context,"noteName").toUpperCase());
									Note note = Note.valueOf(StringArgumentType.getString(context,"noteName").toUpperCase());
									AmethystSong.LOGGER.info("valueOf: " + note.getName());
									SongManager.getSong(IntegerArgumentType.getInteger(context,"songNumber")).addNote(note);
									context.getSource().getPlayer().sendMessage(Text.literal(StringArgumentType.getString(context,"noteName").toUpperCase() + " added to song #" + IntegerArgumentType.getInteger(context,"songNumber") + "!"));
									return 1;
							}
							))))));
			//add new song command
			dispatcher.register(CommandManager.literal(MOD_ID)
					.then(CommandManager.literal("add").executes(context -> {
						Song newSong = new Song();
						SongManager.addSong(newSong);
						int numSongs = SongManager.getNumSongs();
						newSong.setNumSong(numSongs);
						SongPageManager manager = new SongPageManager(newSong);
						newSong.setManager(manager);
						context.getSource().getPlayer().sendMessage(Text.literal("Song " + newSong.getNumSong() + " added!"));
						return 1;
					})));
			dispatcher.register(CommandManager.literal(MOD_ID)
					.then(CommandManager.literal("get")
							.then(CommandManager.argument("songNumber",IntegerArgumentType.integer(0))
								.then(CommandManager.literal("set")
										.then(CommandManager.argument("noteNumber",IntegerArgumentType.integer())
												.then(CommandManager.argument("newNoteName",StringArgumentType.string()).executes(context -> {
													if (SongManager.getSong(IntegerArgumentType.getInteger(context,"songNumber")) != null) {
														int noteNum =  IntegerArgumentType.getInteger(context,"noteNumber");
														Note newNote = Note.valueOf(StringArgumentType.getString(context,"newNoteName").toUpperCase());
														SongManager.getActiveSong().setNote(noteNum, newNote);
														context.getSource().getPlayer().sendMessage(Text.literal("Note " + noteNum + "/" + SongManager.getActiveSong().getNumNotes() + " set to " + newNote.getName() + "!"));
														return 1;
													} else {
														context.getSource().getPlayer().sendMessage(Text.literal("Song does not exist").formatted(Formatting.RED));
														return 1;
													}

												})))))));
			//clear command
			dispatcher.register(CommandManager.literal(MOD_ID)
					.then(CommandManager.literal("get")
							.then(CommandManager.argument("songNumber", IntegerArgumentType.integer(0))
									.then(CommandManager.literal("clear")
											.executes(context -> {
												int songNum = IntegerArgumentType.getInteger(context,"songNumber");
												SongManager.getSong(songNum).getNotes().clear();
												context.getSource().getPlayer().sendMessage(Text.literal("Song #" + songNum + " cleared!"));
												return 1;
											})))));
		}));
		LOGGER.info("AmethystSong initialized");
	}

	private void clientTickEvent(MinecraftClient mc) {
			if (KeybindingRegistrationHelper.openMenuKey.wasPressed()) {
				if (SongManager.getNumSongs() <= 0) {
					mc.player.sendMessage(Text.literal("Could not open song editor: No songs exist yet. Start by typing the /" + MOD_ID + " add command!"));
				} else {
					mc.setScreen(new EditorScreen(new EditorGUI()));
				}

			}
	}
}
