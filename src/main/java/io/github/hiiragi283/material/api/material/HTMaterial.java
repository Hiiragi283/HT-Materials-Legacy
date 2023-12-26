package io.github.hiiragi283.material.api.material;

import com.github.bsideup.jabel.Desugar;
import com.google.common.collect.ImmutableMap;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlag;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.material.property.HTPropertyKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@Desugar
public record HTMaterial(
        int index,
        HTMaterialKey key,
        HTMaterialInfo info,
        HTMaterialPropertyMap properties,
        HTMaterialFlagSet flags
) {

    public void verify() {
        properties.verify(this);
        flags.verify(this);
    }

    //    Properties    //

    @Nullable
    public <T extends HTMaterialProperty<T>> T getProperty(HTPropertyKey<T> key) {
        return properties.getAs(key);
    }

    public boolean hasProperty(HTPropertyKey<?> key) {
        return properties.containsKey(key);
    }

    //    Flags    //

    public boolean hasFlag(HTMaterialFlag flag) {
        return flags.contains(flag);
    }

    //    Object    //

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof HTMaterial otherMaterial) return otherMaterial.key.equals(this.key);
        return false;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return key.toString();
    }

    //    Registry    //

    private static final Logger LOGGER = LogManager.getLogger(HTMaterial.class);

    private static final Map<HTMaterialKey, HTMaterial> keyRegistry = new HashMap<>();

    public static Map<HTMaterialKey, HTMaterial> getKeyRegistry() {
        return ImmutableMap.copyOf(keyRegistry);
    }

    @NotNull
    public static HTMaterial getMaterial(HTMaterialKey key) {
        HTMaterial material = keyRegistry.get(key);
        if (material == null) {
            throw new IllegalStateException("Material: " + key + " is not registered!");
        }
        return material;
    }

    @Nullable
    public static HTMaterial getMaterialOrNull(HTMaterialKey key) {
        return keyRegistry.get(key);
    }

    private static final Map<Integer, HTMaterial> indexRegistry = new HashMap<>();

    public static Map<Integer, HTMaterial> getIndexRegistry() {
        return ImmutableMap.copyOf(indexRegistry);
    }

    @NotNull
    public static HTMaterial getMaterial(int index) {
        HTMaterial material = indexRegistry.get(index);
        if (material == null) {
            throw new IllegalStateException("Material: " + index + " is not registered!");
        }
        return material;
    }

    @Nullable
    public static HTMaterial getMaterialOrNull(int index) {
        return indexRegistry.get(index);
    }

    static HTMaterial create(
            int index,
            HTMaterialKey key,
            HTMaterialInfo info,
            HTMaterialPropertyMap properties,
            HTMaterialFlagSet flags
    ) {
        var material = new HTMaterial(index, key, info, properties, flags);
        keyRegistry.putIfAbsent(key, material);
        indexRegistry.putIfAbsent(index, material);
        LOGGER.info("Material: " + key + " registered!");
        return material;
    }

}