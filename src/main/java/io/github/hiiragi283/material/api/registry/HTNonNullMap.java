package io.github.hiiragi283.material.api.registry;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.material.impl.registry.HTNonNullMapImpl;

public interface HTNonNullMap<K, V> {

    static <K, V> HTNonNullMap<K, V> create(Function<K, V> mapping) {
        return new HTNonNullMapImpl<>(mapping);
    }

    @NotNull
    V getOrCreate(K key);

    void forEach(BiConsumer<K, V> biConsumer);
}
