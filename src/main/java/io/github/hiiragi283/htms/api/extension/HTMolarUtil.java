package io.github.hiiragi283.htms.api.extension;

import java.util.Map;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

public final class HTMolarUtil {

    private HTMolarUtil() {}

    public static <T> double calculateMolar(@NotNull Map<T, Integer> map, @NotNull Function<T, Double> molarFunction) {
        return calculateMolar(HTMapUtil.mapKeys(map, molarFunction));
    }

    public static double calculateMolar(@NotNull Map<Double, Integer> map) {
        return map.entrySet().stream().map(entry -> entry.getKey() * entry.getValue()).reduce(0.0, Double::sum);
    }
}
