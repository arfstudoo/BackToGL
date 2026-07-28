package com.arfstudoo.backtogl.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Mixin(targets = "net.minecraft.client.Options", remap = false)
public abstract class OptionsAutoFixMixin {

    @Inject(method = "load", at = @At("HEAD"))
    private void sanitizeOptionsFile(CallbackInfo ci) {
        try {
            File gameDir = new File(".");
            File optionsFile = new File(gameDir, "options.txt");
            if (optionsFile.exists()) {
                List<String> lines = Files.readAllLines(optionsFile.toPath());
                boolean modified = false;
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    if (line.startsWith("preferredGraphicsBackend:")) {
                        lines.set(i, "preferredGraphicsBackend:opengl");
                        modified = true;
                    }
                }
                if (modified) {
                    Files.write(optionsFile.toPath(), lines);
                }
            }
        } catch (Exception ignored) {
        }
    }
}
