package com.arfstudoo.backtogl;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackToGL implements ModInitializer {

    public static final String MOD_ID = "backtogl";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("BackToGL: Restoring 26.1.2 OpenGL Renderer into Minecraft 26.2");
    }
}
