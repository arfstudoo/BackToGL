package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.mojang.blaze3d.platform.Window", remap = false)
public abstract class WindowMixin {

    @Inject(method = "createGlfwWindow", at = @At("HEAD"))
    private static void forceOpenGLWindowBackend(CallbackInfoReturnable<Long> cir) {
        System.setProperty("blaze3d.backend", "opengl");
    }
}
