package io.github.hiiragi283.htms.api.extension;

import java.util.function.Supplier;

import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;

import org.jetbrains.annotations.NotNull;

public final class HTSideUtil {

    private HTSideUtil() {}

    public static void runWhenOn(@NotNull Side side, @NotNull Runnable runnable) {
        if (FMLLaunchHandler.side() == side) runnable.run();
    }

    public static <T> @NotNull T run(@NotNull Supplier<@NotNull T> client, @NotNull Supplier<@NotNull T> server) {
        T result = null;
        switch (FMLLaunchHandler.side()) {
            case CLIENT -> result = client.get();
            case SERVER -> result = server.get();
        }
        return result;
    }
}
