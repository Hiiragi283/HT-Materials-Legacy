package io.github.hiiragi283.api.extension;

import java.util.function.Supplier;

import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;

import org.jetbrains.annotations.NotNull;

public final class SideUtil {

    private SideUtil() {}

    public static void runWhenOn(@NotNull Side side, @NotNull Runnable runnable) {
        if (FMLLaunchHandler.side() == side) runnable.run();
    }

    public static <T> T runForSide(@NotNull Supplier<T> clientRun, @NotNull Supplier<T> serverRun) {
        if (FMLLaunchHandler.side() == Side.CLIENT) {
            return clientRun.get();
        } else {
            return serverRun.get();
        }
    }
}
