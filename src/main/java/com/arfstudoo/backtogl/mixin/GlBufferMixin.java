package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(targets = "com.mojang.blaze3d.opengl.GlDevice", remap = false)
public abstract class GlBufferMixin {

    @ModifyVariable(
        method = "createBuffer(Ljava/util/function/Supplier;IJ)Lcom/mojang/blaze3d/buffers/GpuBuffer;",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0
    )
    private int fixUboAlignmentForLegacyGpu(int usage) {
        return usage;
    }
}
