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
    public static final String HASH = "<your_serial_here>";

    public void onInitialize(){
        LOGGER.info("Hello");
    }
}
