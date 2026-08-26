package com.example.freelook;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import org.lwjgl.glfw.GLFW;

public class FreelookClient implements ClientModInitializer {

    private static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        // Default key: backslash. Change it any time in Options > Controls > Freelook.
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.freelook.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_BACKSLASH,
                "category.freelook"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                toggle(client);
            }
        });
    }

    private void toggle(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null) {
            return;
        }

        if (!FreelookState.isActive()) {
            // Turning ON: the free camera starts out looking exactly where the player is looking.
            FreelookState.activate(player.getYaw(), player.getPitch());
        } else {
            // Turning OFF: snap the player (and thus movement/body direction) to where
            // the free camera last looked.
            float yaw = FreelookState.getYaw();
            float pitch = FreelookState.getPitch();
            FreelookState.deactivate();

            player.setYaw(yaw);
            player.setPitch(pitch);
            player.setHeadYaw(yaw);
            player.setBodyYaw(yaw);
        }
    }
}
