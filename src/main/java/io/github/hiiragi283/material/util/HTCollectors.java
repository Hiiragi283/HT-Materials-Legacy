package io.github.hiiragi283.material.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

public abstract class HTCollectors {

    private HTCollectors() {
    }

    public static <T, K, V> Collector<T, HashMap<K, V>, HashMap<K, V>> associate(@NotNull Function<T, K> keyFunction, @NotNull Function<T, V> valueFunction) {
        return Collector.of(
                HashMap::new,
                (map, t) -> map.put(keyFunction.apply(t), valueFunction.apply(t)),
                (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                }
        );
    }

    public static <K, V> Collector<K, HashMap<K, V>, HashMap<K, V>> associateWith(@Nullable V value) {
        return Collector.of(
                HashMap::new,
                (map, key) -> map.put(key, value),
                (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                }
        );
    }

    public static <
            K, k, V> Collector<Map.Entry<K, V>, HashMap<k, V>, HashMap<k, V>> mapKeys(@NotNull Function<K, k> function) {
        return Collector.of(
                HashMap::new,
                (map, entry) -> map.put(function.apply(entry.getKey()), entry.getValue()),
                (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                }
        );
    }

}