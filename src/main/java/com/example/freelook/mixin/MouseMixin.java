package com.example.freelook.mixin;

import com.example.freelook.FreelookState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class MouseMixin {

    /**
     * Every mouse-look update ultimately calls Entity#changeLookDirection on the
     * player. While freelook is active we intercept it here directly (rather than
     * hooking Mouse's internals, which change between versions) and redirect the
     * same delta into FreelookState instead, cancelling the real turn so the
     * player's actual facing is left completely untouched.
     */
    @Inject(method = "changeLookDirection", at = @At("HEAD"), cancellable = true)
    private void freelook$onChangeLookDirection(double cursorDeltaX, double cursorDeltaY, CallbackInfo ci) {
        if (!FreelookState.isActive()) {
            return;
        }
        Object self = this;
        if (self instanceof ClientPlayerEntity && MinecraftClient.getInstance().player == self) {
            FreelookState.applyLookDelta(cursorDeltaX, cursorDeltaY);
            ci.cancel();
        }
    }
}
