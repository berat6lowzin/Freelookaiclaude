package com.example.freelook;

import net.minecraft.util.math.MathHelper;

/**
 * Holds the state of the "free" camera: whether it's active, and the yaw/pitch
 * it currently points at. While active, mouse movement updates THIS yaw/pitch
 * instead of the player entity's real rotation (see MouseMixin). The Camera
 * itself is then redirected to look at this yaw/pitch instead of the player's
 * (see CameraMixin).
 */
public class FreelookState {
    private static boolean active = false;
    private static float yaw;
    private static float pitch;

    public static boolean isActive() {
        return active;
    }

    public static float getYaw() {
        return yaw;
    }

    public static float getPitch() {
        return pitch;
    }

    /** Turn freelook ON, starting the free camera at the player's current facing. */
    public static void activate(float startYaw, float startPitch) {
        yaw = startYaw;
        pitch = startPitch;
        active = true;
    }

    /** Turn freelook OFF. Caller is responsible for snapping the player to (yaw, pitch). */
    public static void deactivate() {
        active = false;
    }

    /**
     * Applies a mouse-look delta to the free camera's yaw/pitch, using the same
     * math vanilla uses in Entity#changeLookDirection, so sensitivity feels identical.
     */
    public static void applyLookDelta(double cursorDeltaX, double cursorDeltaY) {
        float f = (float) cursorDeltaY * 0.15f;
        float g = (float) cursorDeltaX * 0.15f;
        pitch = MathHelper.clamp(pitch + f, -90.0f, 90.0f);
        yaw = MathHelper.wrapDegrees(yaw + g);
    }
}
