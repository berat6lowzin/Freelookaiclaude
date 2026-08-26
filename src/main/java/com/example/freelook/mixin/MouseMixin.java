package com.example.freelook.mixin;

import com.example.freelook.FreelookState;
import net.minecraft.client.Mouse;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Mouse.class)
public class MouseMixin {

    /**
     * Vanilla's Mouse#updateMouse calls Entity#changeLookDirection on the player
     * every frame to turn the player's head/body with the mouse. While freelook is
     * active, we intercept that call and redirect the same delta into FreelookState
     * instead, so the player's real facing is left completely untouched.
     */
    @Redirect(
            method = "updateMouse",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;changeLookDirection(DD)V"
            )
    )
    private void freelook$redirectLookDirection(Entity entity, double cursorDeltaX, double cursorDeltaY) {
        if (FreelookState.isActive()) {
            FreelookState.applyLookDelta(cursorDeltaX, cursorDeltaY);
        } else {
            entity.changeLookDirection(cursorDeltaX, cursorDeltaY);
        }
    }
}
