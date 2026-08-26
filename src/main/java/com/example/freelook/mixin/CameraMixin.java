package com.example.freelook.mixin;

import com.example.freelook.FreelookState;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {

    // Gives us access to Camera's own private setRotation(float, float), so we reuse
    // vanilla's exact rotation math instead of guessing at internal fields.
    @Invoker("setRotation")
    public abstract void freelook$setRotation(float yaw, float pitch);

    @Inject(method = "update", at = @At("TAIL"))
    private void freelook$overrideRotation(CallbackInfo ci) {
        if (FreelookState.isActive()) {
            this.freelook$setRotation(FreelookState.getYaw(), FreelookState.getPitch());
        }
    }
}
