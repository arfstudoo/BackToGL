package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.screens.options.VideoSettingsScreen", remap = false)
public abstract class VideoSettingsScreenMixin {

    @Inject(method = "addTitle", at = @At("RETURN"))
    private void lockGraphicsOptionNotice(CallbackInfo ci) {
    }
}
