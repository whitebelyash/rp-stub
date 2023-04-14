package ru.whbex.reprotect_stub;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("RPStub");
    public static final String REPROTECT_NAMESPACE = "protect";
    public static final String REPROTECT_PATH = "serial";
    public static final String REPROTECT_VERSION = "0.2";
    // TODO: Random hash generation
    // enter your hash here
    public static final String HASH = null;

    public void onInitialize(){
        LOGGER.info("Hello");
        LOGGER.info("Running RPStub for RProtect {}", REPROTECT_VERSION);
        LOGGER.info("Channel ID: {}:{}", REPROTECT_NAMESPACE, REPROTECT_PATH);
        LOGGER.info("Hash: {}", HASH);
        if(HASH == null)
            throw new RuntimeException("Hash is null!");
    }
}
