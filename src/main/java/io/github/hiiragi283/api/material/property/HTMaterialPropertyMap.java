package io.github.hiiragi283.api.material.property;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.api.material.HTMaterial;

public final class HTMaterialPropertyMap {

    private final Map<HTPropertyKey<?>, HTMaterialProperty<?>> backingMap;

    private HTMaterialPropertyMap(Map<HTPropertyKey<?>, HTMaterialProperty<?>> backingMap) {
        this.backingMap = backingMap;
    }

    public boolean contains(@NotNull HTPropertyKey<?> key) {
        return backingMap.containsKey(key);
    }

    public boolean containsAll(@NotNull Collection<HTPropertyKey<?>> keys) {
        return keys.stream().allMatch(this::contains);
    }

    @Nullable
    public HTMaterialProperty<?> get(@NotNull HTPropertyKey<?> key) {
        return backingMap.get(key);
    }

    public void verify(@NotNull HTMaterial material) {
        backingMap.values().forEach(property -> property.verify(material));
    }

    // Map

    @NotNull
    public Set<HTPropertyKey<?>> keySet() {
        return backingMap.keySet();
    }

    @NotNull
    public Collection<HTMaterialProperty<?>> values() {
        return backingMap.values();
    }

    @NotNull
    public Set<Map.Entry<HTPropertyKey<?>, HTMaterialProperty<?>>> entrySet() {
        return backingMap.entrySet();
    }

    // Builder

    public static final class Builder {

        private final Map<HTPropertyKey<?>, HTMaterialProperty<?>> backingMap = new HashMap<>();

        public <T extends HTMaterialProperty<T>> Builder add(@NotNull T property) {
            return add(property, prop -> {});
        }

        public <T extends HTMaterialProperty<T>> Builder add(@NotNull T property, @NotNull Consumer<T> consumer) {
            consumer.accept(property);
            backingMap.put(property.getKey(), property);
            return this;
        }

        public HTMaterialPropertyMap build() {
            return new HTMaterialPropertyMap(backingMap);
        }
    }
}
