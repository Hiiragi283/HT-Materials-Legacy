package io.github.hiiragi283.material.util;

import io.github.hiiragi283.material.HMReference;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

import java.util.StringJoiner;
import java.util.stream.Stream;

public class HTUtils {

    public static String joinToString(String delimiter, Stream<String> stream) {
        StringJoiner sj = new StringJoiner(delimiter);
        stream.forEach(sj::add);
        return sj.toString();
    }

    public static ModContainer getActiveModContainer() {
        return Loader.instance().activeModContainer();
    }

    public static boolean isHTMaterialsActive() {
        return getActiveModContainer().getModId().equals(HMReference.MOD_ID);
    }

}