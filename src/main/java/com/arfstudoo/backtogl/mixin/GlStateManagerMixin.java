package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.mojang.blaze3d.opengl.GlStateManager", remap = false)
public abstract class GlStateManagerMixin {

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void enforceOpenGLState(CallbackInfo ci) {
        try {
            Class<?> clazz = Class.forName("com.mojang.blaze3d.opengl.GlStateManager");
            java.lang.reflect.Method enableDepth = clazz.getMethod("_enableDepthTest");
            java.lang.reflect.Method depthFunc = clazz.getMethod("_depthFunc", int.class);
            enableDepth.invoke(null);
            depthFunc.invoke(null, 515);
        } catch (Exception ignored) {
        }
    }
}
