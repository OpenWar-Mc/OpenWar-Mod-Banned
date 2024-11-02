package com.openwar.charpy.Mixin;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderManager.class)
public class MixinRenderManager {
    @Inject(method = "renderDebugBoundingBox", at = @At("HEAD"), cancellable = true)
    private void preventRenderDebugBoundingBox(Entity entityIn, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {

        ci.cancel();
    }
}