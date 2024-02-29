package io.github.hiiragi283.api.material;

import java.util.*;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Preconditions;

import io.github.hiiragi283.api.material.composition.HTMaterialComposition;
import io.github.hiiragi283.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.api.material.property.HTMaterialPropertyMap;

public final class HTMaterialHelper {

    // Material Key
    private final Map<HTMaterialKey, Integer> keyMap = new LinkedHashMap<>();

    @NotNull
    public Map<HTMaterialKey, Integer> getKeyMap() {
        return Collections.unmodifiableMap(keyMap);
    }

    public void addMaterialKey(@NotNull HTMaterialKey materialKey, int index) {
        Preconditions.checkArgument(!keyMap.containsKey(materialKey), "MaterialKey; %s is already registered!",
                materialKey.name());
        Preconditions.checkArgument(!keyMap.containsValue(index), "Index; %s is already registered!", index);
        keyMap.put(materialKey, index);
    }

    // Material Composition
    private final Map<HTMaterialKey, HTMaterialComposition> compositionMap = new HashMap<>();

    @NotNull
    public HTMaterialComposition getComposition(@NotNull HTMaterialKey materialKey) {
        return compositionMap.getOrDefault(materialKey, HTMaterialComposition.EMPTY);
    }

    public void setComposition(@NotNull HTMaterialKey materialKey, @NotNull HTMaterialComposition composition) {
        compositionMap.put(materialKey, composition);
    }

    // Material Flag
    private final Map<HTMaterialKey, HTMaterialFlagSet.Builder> flagMap = new HashMap<>();

    @NotNull
    public HTMaterialFlagSet.Builder flagSet(@NotNull HTMaterialKey materialKey) {
        return flagMap.computeIfAbsent(materialKey, key -> new HTMaterialFlagSet.Builder());
    }

    // Material Property
    private final Map<HTMaterialKey, HTMaterialPropertyMap.Builder> propertyMap = new HashMap<>();

    @NotNull
    public HTMaterialPropertyMap.Builder propertyMap(@NotNull HTMaterialKey materialKey) {
        return propertyMap.computeIfAbsent(materialKey, key -> new HTMaterialPropertyMap.Builder());
    }
}
