package io.github.hiiragi283.htms.api.material;

import java.util.*;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.material.composition.HTElement;
import io.github.hiiragi283.htms.api.material.composition.HTMaterialComposition;

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

    public @NotNull HTMaterial getOrEmpty(@NotNull HTMaterialKey key) {
        return keyMap.getOrDefault(key, HTMaterial.empty(key));
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

        public @NotNull HTMaterial.Builder create(@NotNull HTMaterialKey key, int index) {
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

        public @Nullable HTMaterial.Builder get(@NotNull HTMaterialKey key) {
            return builderMap.get(key);
        }

        public @NotNull HTMaterial.Builder getOrCreate(@NotNull HTMaterialKey key, int index) {
            if (builderMap.containsKey(key)) {
                return Objects.requireNonNull(get(key));
            } else {
                return create(key, index);
            }
        }

        // Util //

        public @NotNull HTMaterial.Builder addSimpleMaterial(@NotNull HTMaterialKey key, int index,
                                                             @NotNull HTElement element, int elementCount,
                                                             @Nullable HTMaterialType type) {
            return addSimpleMaterial(key, index,
                    HTMaterialComposition.molecular(ImmutableMap.of(element, elementCount)), type);
        }

        private @NotNull HTMaterial.Builder addSimpleMaterial(@NotNull HTMaterialKey key, int index,
                                                              @NotNull HTMaterialComposition composition,
                                                              @Nullable HTMaterialType type) {
            return getOrCreate(key, index)
                    .setComposition(composition)
                    .setType(type);
        }

        // Gem //

        public @NotNull HTMaterial.Builder addGemMaterial(@NotNull HTMaterialKey key, int index,
                                                          @NotNull HTElement element, @NotNull HTMaterialType.Gem type,
                                                          boolean isAdvanced) {
            return addGemMaterial(key, index, element, 1, type, isAdvanced);
        }

        public @NotNull HTMaterial.Builder addGemMaterial(@NotNull HTMaterialKey key, int index,
                                                          @NotNull HTElement element, int elementCount,
                                                          @NotNull HTMaterialType.Gem type, boolean isAdvanced) {
            return addGemMaterial(key, index, HTMaterialComposition.molecular(ImmutableMap.of(element, elementCount)),
                    type, isAdvanced);
        }

        public @NotNull HTMaterial.Builder addGemMaterial(@NotNull HTMaterialKey key, int index,
                                                          @NotNull HTMaterialComposition composition,
                                                          @NotNull HTMaterialType.Gem type, boolean isAdvanced) {
            HTMaterial.Builder builder = addSimpleMaterial(key, index, composition, type)
                    .addFlag(HTMaterialFlags.GENERATE_DUST)
                    .addFlag(HTMaterialFlags.GENERATE_GEM)
                    .addFlag(HTMaterialFlags.GENERATE_PLATE);
            if (isAdvanced) {
                builder
                        .addFlag(HTMaterialFlags.GENERATE_GEAR)
                        .addFlag(HTMaterialFlags.GENERATE_ROD);
            }
            return builder;
        }

        // Metal //

        public @NotNull HTMaterial.Builder addMetalMaterial(@NotNull HTMaterialKey key, int index,
                                                            @NotNull HTElement element,
                                                            @NotNull HTMaterialType.Metal type, boolean isAdvanced) {
            return addMetalMaterial(key, index, element, 1, type, isAdvanced);
        }

        public @NotNull HTMaterial.Builder addMetalMaterial(@NotNull HTMaterialKey key, int index,
                                                            @NotNull HTElement element, int elementCount,
                                                            @NotNull HTMaterialType.Metal type, boolean isAdvanced) {
            return addMetalMaterial(key, index, HTMaterialComposition.molecular(ImmutableMap.of(element, elementCount)),
                    type, isAdvanced);
        }

        public @NotNull HTMaterial.Builder addMetalMaterial(@NotNull HTMaterialKey key, int index,
                                                            @NotNull HTMaterialComposition composition,
                                                            @NotNull HTMaterialType.Metal type, boolean isAdvanced) {
            HTMaterial.Builder builder = addSimpleMaterial(key, index, composition, type)
                    .addFlag(HTMaterialFlags.GENERATE_DUST)
                    .addFlag(HTMaterialFlags.GENERATE_INGOT)
                    .addFlag(HTMaterialFlags.GENERATE_NUGGET)
                    .addFlag(HTMaterialFlags.GENERATE_PLATE);
            if (isAdvanced) {
                builder
                        .addFlag(HTMaterialFlags.GENERATE_GEAR)
                        .addFlag(HTMaterialFlags.GENERATE_ROD);
            }
            return builder;
        }
    }
}
