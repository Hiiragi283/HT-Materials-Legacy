package io.github.hiiragi283.htms.api;

import net.minecraftforge.fml.relauncher.FMLLaunchHandler;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class HTLogger {

    private final Logger logger;

    public HTLogger(@NotNull String name) {
        this.logger = LogManager.getLogger(name);
    }

    private void log(@NotNull String message, @NotNull Level level) {
        logger.log(level, message);
    }

    public void info(@NotNull String message) {
        log(message, Level.INFO);
    }

    public void warn(@NotNull String message) {
        log(message, Level.WARN);
    }

    public void error(@NotNull String message) {
        log(message, Level.ERROR);
    }

    public void fatal(@NotNull String message) {
        log(message, Level.FATAL);
    }

    public void debug(@NotNull String message) {
        if (FMLLaunchHandler.isDeobfuscatedEnvironment()) {
            info(message);
        }
    }
}
