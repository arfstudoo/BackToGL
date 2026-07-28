package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.renderer.RenderStateShard", remap = false)
public abstract class RenderTypeMixin {

    @Inject(method = "setupRenderState", at = @At("HEAD"))
    private void enforceTranslucentDepthState(CallbackInfo ci) {
        try {
            Class<?> clazz = Class.forName("com.mojang.blaze3d.opengl.GlStateManager");
            java.lang.reflect.Method enableBlend = clazz.getMethod("_enableBlend");
            java.lang.reflect.Method depthMask = clazz.getMethod("_depthMask", boolean.class);
            enableBlend.invoke(null);
            depthMask.invoke(null, true);
        } catch (Exception ignored) {
        }
    }
}
