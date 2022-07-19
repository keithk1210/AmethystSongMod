package net.fabricmc.amethystsong;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.amethystsong.gui.EditorGUI;
import net.fabricmc.amethystsong.gui.EditorScreen;
import net.fabricmc.amethystsong.gui.SongGUI;
import net.fabricmc.amethystsong.gui.SongScreen;
import net.fabricmc.amethystsong.keybindings.KeybindingRegistrationHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.security.Key;

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
		LOGGER.info("AmethystSong initialized");
	}

	private void clientTickEvent(MinecraftClient mc) {
		if (KeybindingRegistrationHelper.openMenuKey.wasPressed()) {
			mc.setScreen(new EditorScreen(new EditorGUI()));
		}

	}
}
