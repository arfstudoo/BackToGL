package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(targets = "net.minecraft.client.gui.components.DebugScreenOverlay", remap = false)
public abstract class DebugHudMixin {

    @Inject(
        method = "extractLines(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Ljava/util/List;Z)V",
        at = @At("HEAD")
    )
    private void addBackToGLInfo(@Coerce Object graphics, List<String> lines, boolean alignLeft, CallbackInfo ci) {
        if (alignLeft && lines != null) {
            lines.add("");
            lines.add("§a[BackToGL] Renderer: OpenGL (Vulkan locked)");
        }
    }
}
