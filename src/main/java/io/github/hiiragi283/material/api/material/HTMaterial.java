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

import java.util.LinkedHashMap;
import java.util.Map;

@Desugar
public class HTMaterial {

    private final HTMaterialKey key;

    private final HTMaterialInfo info;

    private final HTMaterialPropertyMap properties;

    private final HTMaterialFlagSet flags;

    private HTMaterial(HTMaterialKey key, HTMaterialInfo info, HTMaterialPropertyMap properties, HTMaterialFlagSet flags) {
        this.key = key;
        this.info = info;
        this.properties = properties;
        this.flags = flags;
    }

    public HTMaterialKey getKey() {
        return key;
    }

    public int getIndex() {
        return getKey().index();
    }

    public String getName() {
        return getKey().name();
    }

    public HTMaterialInfo getInfo() {
        return info;
    }

    public HTMaterialPropertyMap getProperties() {
        return properties;
    }

    public HTMaterialFlagSet getFlags() {
        return flags;
    }

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

    private static final Logger LOGGER = LogManager.getLogger("HTMaterial");

    private static final Map<HTMaterialKey, HTMaterial> registry = new LinkedHashMap<>();

    public static Map<HTMaterialKey, HTMaterial> getRegistry() {
        return ImmutableMap.copyOf(registry);
    }

    @NotNull
    public static HTMaterial getMaterial(HTMaterialKey key) {
        HTMaterial material = getMaterialOrNull(key);
        if (material == null) {
            throw new IllegalStateException("Material: " + key + " is not registered!");
        }
        return material;
    }

    @Nullable
    public static HTMaterial getMaterialOrNull(HTMaterialKey key) {
        return registry.get(key);
    }

    private static final Map<Integer, HTMaterial> indexRegistry = new LinkedHashMap<>();

    public static Map<Integer, HTMaterial> getIndexRegistry() {
        return ImmutableMap.copyOf(indexRegistry);
    }

    @NotNull
    public static HTMaterial getMaterial(int index) {
        HTMaterial material = getMaterialOrNull(index);
        if (material == null) {
            throw new IllegalStateException("Material: " + index + " is not registered!");
        }
        return material;
    }

    @Nullable
    public static HTMaterial getMaterialOrNull(int index) {
        return indexRegistry.get(index);
    }

    static void create(
            HTMaterialKey key,
            HTMaterialInfo info,
            HTMaterialPropertyMap properties,
            HTMaterialFlagSet flags
    ) {
        var material = new HTMaterial(key, info, properties, flags);
        if (registry.putIfAbsent(key, material) != null) {
            HTMaterial existMaterial = registry.get(key);
            throw new IllegalStateException("Name: " + key.name() + " is already registered by " + existMaterial);
        }
        if (indexRegistry.putIfAbsent(key.index(), material) != null) {
            HTMaterial existMaterial = indexRegistry.get(key.index());
            throw new IllegalStateException("Index: " + key.index() + " is already registered by " + existMaterial);
        }
        LOGGER.info("Material: " + key + " registered!");
    }

}