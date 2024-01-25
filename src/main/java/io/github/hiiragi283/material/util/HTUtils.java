package io.github.hiiragi283.material.util;

import java.util.StringJoiner;
import java.util.stream.Stream;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.material.HMReference;

public abstract class HTUtils {

    private HTUtils() {}

    @NotNull
    public static String joinToString(@NotNull String delimiter, @NotNull Stream<String> stream) {
        StringJoiner sj = new StringJoiner(delimiter);
        stream.forEach(sj::add);
        return sj.toString();
    }

    @Nullable
    public static ModContainer getActiveModContainer() {
        return Loader.instance().activeModContainer();
    }

    public static boolean isHTMaterialsActive() {
        ModContainer activeMod = getActiveModContainer();
        return activeMod != null && activeMod.getModId().equals(HMReference.MOD_ID);
    }
}
