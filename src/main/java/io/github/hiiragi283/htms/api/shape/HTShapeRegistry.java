package io.github.hiiragi283.htms.api.shape;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;

public final class HTShapeRegistry {

    @NotNull
    private final Map<@NotNull String, @NotNull HTShape> keyMap = new LinkedHashMap<>();

    public HTShapeRegistry(HTShapeRegistry.Builder builder) {
        builder.shapes.stream()
                .sorted()
                .map(HTShape::new)
                .forEach(shape -> {
                    keyMap.put(shape.name(), shape);
                    HTMaterialsAPI.LOGGER.debug("Registered shape; " + shape);
                });
    }

    public boolean containsKey(@NotNull String key) {
        return keyMap.containsKey(key);
    }

    public @NotNull Set<@NotNull String> keySet() {
        return keyMap.keySet();
    }

    public @NotNull Collection<@NotNull HTShape> values() {
        return keyMap.values();
    }

    public @Nullable HTShape get(@NotNull String key) {
        return keyMap.get(key);
    }

    public @NotNull ImmutableMap<@NotNull String, @NotNull HTShape> keyMap() {
        return ImmutableMap.copyOf(keyMap);
    }

    public static final class Builder {

        List<String> shapes = new ArrayList<>();

        public void add(HTShape shape) {
            add(shape.name());
        }

        public void add(String name) {
            shapes.add(name);
        }

        public void remove(HTShape shape) {
            remove(shape.name());
        }

        public void remove(String name) {
            shapes.remove(name);
        }
    }
}
