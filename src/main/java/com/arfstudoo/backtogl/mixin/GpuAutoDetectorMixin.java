package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "com.mojang.blaze3d.opengl.GlDevice", remap = false)
public abstract class GpuAutoDetectorMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void detectLegacyGpuAndApplyWorkarounds(CallbackInfo ci) {
        try {
            Class<?> gl11 = Class.forName("org.lwjgl.opengl.GL11");
            java.lang.reflect.Method getString = gl11.getMethod("glGetString", int.class);
            String renderer = (String) getString.invoke(null, 7937);
            if (renderer != null) {
                String lower = renderer.toLowerCase();
                if (lower.contains("geforce 7") || lower.contains("gt 7") || lower.contains("intel hd 3000") || lower.contains("intel hd 4000") || lower.contains("radeon hd")) {
                    System.setProperty("blaze3d.disable_dsa", "true");
                    System.setProperty("blaze3d.legacy_alignment", "true");
                }
            }
        } catch (Exception ignored) {
        }
    }
}
