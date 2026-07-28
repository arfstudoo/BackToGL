package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "com.mojang.blaze3d.opengl.GlBuffer$Direct", remap = false)
public abstract class GlBufferDirectMapFixMixin {

    @Inject(method = "map", at = @At("HEAD"))
    private void patchDirectUsageBeforeMap(long offset, long length, boolean read, boolean write, CallbackInfoReturnable<Object> cir) {
        try {
            Class<?> currentClass = this.getClass();
            while (currentClass != null && currentClass != Object.class) {
                try {
                    java.lang.reflect.Field flagsField = currentClass.getDeclaredField("mappingFlags");
                    flagsField.setAccessible(true);
                    int currentFlags = flagsField.getInt(this);
                    if (write && (currentFlags & 50) == 0) {
                        flagsField.setInt(this, currentFlags | 50);
                    }
                    break;
                } catch (NoSuchFieldException e) {
                    currentClass = currentClass.getSuperclass();
                }
            }
        } catch (Exception ignored) {
        }

        if (write) {
            try {
                Class<?> parentClass = this.getClass().getSuperclass();
                while (parentClass != null && parentClass != Object.class) {
                    try {
                        java.lang.reflect.Field usageField = parentClass.getDeclaredField("usage");
                        usageField.setAccessible(true);
                        int current = usageField.getInt(this);
                        if ((current & 2) == 0) {
                            usageField.setInt(this, current | 2);
                        }
                        break;
                    } catch (NoSuchFieldException e) {
                        parentClass = parentClass.getSuperclass();
                    }
                }
            } catch (Exception ignored) {
            }
        }
    }
}
