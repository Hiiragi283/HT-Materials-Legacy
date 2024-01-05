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

    private static <K, V> Map<K, V> combineMap(Map<K, V> map1, Map<K, V> map2) {
        map1.putAll(map2);
        return map1;
    }

    public static <T, K, V> Collector<T, Map<K, V>, Map<K, V>> associate(@NotNull Function<T, K> keyFunction, @NotNull Function<T, V> valueFunction) {
        return Collector.of(
                HashMap::new,
                (map, t) -> map.put(keyFunction.apply(t), valueFunction.apply(t)),
                HTCollectors::combineMap
        );
    }

    public static <K, V> Collector<K, Map<K, V>, Map<K, V>> associateWith(@Nullable V value) {
        return Collector.of(
                HashMap::new,
                (map, key) -> map.put(key, value),
                HTCollectors::combineMap
        );
    }

    public static <K, L, V> Collector<Map.Entry<K, V>, Map<L, V>, Map<L, V>> mapKeys(@NotNull Function<K, L> function) {
        return Collector.of(
                HashMap::new,
                (map, entry) -> map.put(function.apply(entry.getKey()), entry.getValue()),
                HTCollectors::combineMap
        );
    }

}