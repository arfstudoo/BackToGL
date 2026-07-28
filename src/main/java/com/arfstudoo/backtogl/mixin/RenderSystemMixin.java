package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.mojang.blaze3d.systems.RenderSystem", remap = false)
public abstract class RenderSystemMixin {

    @Inject(method = "initBackendSystem", at = @At("HEAD"))
    private static void forceOpenGLBackend(CallbackInfoReturnable<Object> cir) {
        System.setProperty("blaze3d.backend", "opengl");
    }
}
