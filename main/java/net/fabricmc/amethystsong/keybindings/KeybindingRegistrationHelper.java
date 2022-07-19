package net.fabricmc.amethystsong.keybindings;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeybindingRegistrationHelper {

    public KeybindingRegistrationHelper() {
    }

    public static KeyBinding openMenuKey;

    public static void register() {

        openMenuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.amethystsong.openmenu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_M,
                "category.amethystsong.open"));
    }
}
