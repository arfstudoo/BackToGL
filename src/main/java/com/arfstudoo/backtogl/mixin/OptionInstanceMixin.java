package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.OptionInstance", remap = false)
public abstract class OptionInstanceMixin {

    @Inject(method = "createButton", at = @At("RETURN"))
    private void lockGraphicsApiButton(CallbackInfoReturnable<Object> cir) {
        Object widget = cir.getReturnValue();
        if (widget != null) {
            try {
                java.lang.reflect.Method getMessage = widget.getClass().getMethod("getMessage");
                Object msg = getMessage.invoke(widget);
                if (msg != null && msg.toString().toLowerCase().contains("graphics")) {
                    java.lang.reflect.Field activeField = widget.getClass().getField("active");
                    activeField.setBoolean(widget, false);
                }
            } catch (Exception ignored) {
            }
        }
    }
}
