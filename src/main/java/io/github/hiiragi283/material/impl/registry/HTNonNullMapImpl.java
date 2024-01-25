package io.github.hiiragi283.material.impl.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.material.api.registry.HTNonNullMap;

public final class HTNonNullMapImpl<K, V> implements HTNonNullMap<K, V> {

    private final Map<K, V> backingMap = new HashMap<>();

    private final Function<K, V> mapping;

    public HTNonNullMapImpl(Function<K, V> mapping) {
        this.mapping = mapping;
    }

    @NotNull
    @Override
    public V getOrCreate(K key) {
        return backingMap.computeIfAbsent(key, mapping);
    }

    @Override
    public void forEach(BiConsumer<K, V> biConsumer) {
        backingMap.forEach(biConsumer);
    }
}
