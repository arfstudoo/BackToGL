package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.mojang.blaze3d.buffers.GpuBuffer", remap = false)
public abstract class GpuBufferUsageFlagsFixMixin {

    @Inject(method = "usage", at = @At("RETURN"), cancellable = true)
    private void addAllEssentialBufferFlags(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(cir.getReturnValue() | 32 | 64 | 128 | 8 | 16 | 2 | 1);
    }
}
