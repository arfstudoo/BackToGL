package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "com.mojang.blaze3d.opengl.BufferStorage", remap = false)
public abstract class BufferStorageForceMutableMixin {

    @Redirect(
        method = "create",
        at = @At(
            value = "FIELD",
            target = "Lcom/mojang/blaze3d/opengl/GlDevice;USE_GL_ARB_buffer_storage:Z"
        )
    )
    private static boolean forceDisableBufferStorage() {
        return false;
    }
}
