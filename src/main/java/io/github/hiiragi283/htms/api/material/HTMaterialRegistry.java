package io.github.hiiragi283.htms.api.material;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;

public final class HTMaterialRegistry {

    @NotNull
    private final Map<@NotNull HTMaterialKey, @NotNull HTMaterial> keyMap = new LinkedHashMap<>();

    @NotNull
    private final Map<@NotNull Integer, @NotNull HTMaterial> indexMap = new LinkedHashMap<>();

    public HTMaterialRegistry(HTMaterialRegistry.Builder builder) {
        builder.builderMap.entrySet()
                .stream()
                .map(HTMaterial::new)
                .sorted(Comparator.comparingInt(HTMaterial::index))
                .forEach(material -> {
                    keyMap.put(material.key(), material);
                    indexMap.put(material.index(), material);
                    HTMaterialsAPI.LOGGER.debug("Registered material; " + material.key());
                });
    }

    public boolean containsKey(@NotNull HTMaterialKey key) {
        return keyMap.containsKey(key);
    }

    public boolean containsIndex(int index) {
        return indexMap.containsKey(index);
    }

    public @NotNull Set<@NotNull HTMaterialKey> keySet() {
        return keyMap.keySet();
    }

    public @NotNull Set<@NotNull Integer> indexSet() {
        return indexMap.keySet();
    }

    public @NotNull Collection<@NotNull HTMaterial> values() {
        return keyMap.values();
    }

    public @Nullable HTMaterial get(@NotNull HTMaterialKey key) {
        return keyMap.get(key);
    }

    public @Nullable HTMaterial get(int index) {
        return indexMap.get(index);
    }

    public @NotNull ImmutableMap<@NotNull HTMaterialKey, @NotNull HTMaterial> keyMap() {
        return ImmutableMap.copyOf(keyMap);
    }

    public @NotNull ImmutableMap<@NotNull Integer, @NotNull HTMaterial> indexMap() {
        return ImmutableMap.copyOf(indexMap);
    }

    // Builder //

    public static final class Builder {

        @NotNull
        Map<@NotNull HTMaterialKey, HTMaterial.@NotNull Builder> builderMap = new LinkedHashMap<>();

        @NotNull
        private final Map<@NotNull Integer, @NotNull HTMaterialKey> indexCheckMap = new HashMap<>();

        public HTMaterial.Builder create(@NotNull HTMaterialKey key, int index) {
            if (builderMap.containsKey(key)) {
                throw new IllegalArgumentException("Material Key; " + key + " already registered!");
            } else if (builderMap.values().stream().anyMatch(builder -> builder.index == index)) {
                throw new IllegalArgumentException(
                        "Material index; " + index + " already registered by !" + indexCheckMap.get(index));
            } else {
                indexCheckMap.put(index, key);
                return builderMap.computeIfAbsent(key, key1 -> new HTMaterial.Builder(index));
            }
        }

        @Nullable
        public HTMaterial.Builder get(@NotNull HTMaterialKey key) {
            return builderMap.get(key);
        }

        @NotNull
        public HTMaterial.Builder getOrCreate(@NotNull HTMaterialKey key, int index) {
            if (builderMap.containsKey(key)) {
                return Objects.requireNonNull(get(key));
            } else {
                return create(key, index);
            }
        }
    }
}
