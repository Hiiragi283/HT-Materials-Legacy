package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.HTMaterial;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public final class HTMaterialPropertyMap implements Map<HTPropertyKey<?>, HTMaterialProperty<?>> {

    private final Map<HTPropertyKey<?>, HTMaterialProperty<?>> backingMap;

    private HTMaterialPropertyMap(Map<HTPropertyKey<?>, HTMaterialProperty<?>> backingMap) {
        this.backingMap = backingMap;
    }

    @Nullable
    public <T extends HTMaterialProperty<T>> T getAs(HTPropertyKey<T> key) {
        return key.getObjClass().cast(this.get(key));
    }

    public void verify(HTMaterial material) {
        backingMap.values().forEach(property -> property.verify(material));
    }

    //    Map    //

    @Override
    public int size() {
        return backingMap.size();
    }

    @Override
    public boolean isEmpty() {
        return backingMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return backingMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return backingMap.containsValue(value);
    }

    @Override
    public HTMaterialProperty<?> get(Object key) {
        return backingMap.get(key);
    }

    @Nullable
    @Override
    public HTMaterialProperty<?> put(HTPropertyKey<?> key, HTMaterialProperty<?> value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HTMaterialProperty<?> remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(@NotNull Map<? extends HTPropertyKey<?>, ? extends HTMaterialProperty<?>> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @NotNull
    @Override
    public Set<HTPropertyKey<?>> keySet() {
        return backingMap.keySet();
    }

    @NotNull
    @Override
    public Collection<HTMaterialProperty<?>> values() {
        return backingMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<HTPropertyKey<?>, HTMaterialProperty<?>>> entrySet() {
        return backingMap.entrySet();
    }

    //    Builder    //

    public static class Builder {

        private final Map<HTPropertyKey<?>, HTMaterialProperty<?>> backingMap = new HashMap<>();

        public <T extends HTMaterialProperty<T>> void add(T property) {
            add(property, prop -> {
            });
        }

        public <T extends HTMaterialProperty<T>> void add(T property, Consumer<T> consumer) {
            consumer.accept(property);
            backingMap.put(property.getKey(), property);
        }

        public HTMaterialPropertyMap build() {
            return new HTMaterialPropertyMap(backingMap);
        }

    }

}