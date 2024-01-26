package io.github.hiiragi283.material.api.material;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.material.api.material.flag.HTMaterialFlag;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.material.property.HTPropertyKey;

public final class HTMaterial {

    private final HTMaterialKey key;
    private final HTMaterialPropertyMap properties;
    private final HTMaterialFlagSet flags;
    private final Color color;
    private final String formula;
    private final double molar;

    private HTMaterial(
                       HTMaterialKey key,
                       HTMaterialPropertyMap properties,
                       HTMaterialFlagSet flags,
                       Color color,
                       String formula,
                       double molar) {
        this.key = key;
        this.properties = properties;
        this.flags = flags;
        this.color = color;
        this.formula = formula;
        this.molar = molar;
    }

    public HTMaterialKey key() {
        return key;
    }

    public int index() {
        return key().index();
    }

    public String name() {
        return key().name();
    }

    public Color color() {
        return color;
    }

    public String formula() {
        return formula;
    }

    public double molar() {
        return molar;
    }

    public HTMaterialPropertyMap getProperties() {
        return properties;
    }

    public HTMaterialFlagSet getFlags() {
        return flags;
    }

    @GroovyBlacklist
    public void verify() {
        properties.verify(this);
        flags.verify(this);
    }

    // Properties //

    @Nullable
    public <T extends HTMaterialProperty<T>> T getProperty(HTPropertyKey<T> key) {
        return properties.getAs(key);
    }

    public boolean hasProperty(HTPropertyKey<?> key) {
        return properties.contains(key);
    }

    // Flags //

    public boolean hasFlag(HTMaterialFlag flag) {
        return flags.contains(flag);
    }

    public boolean hasAllFlag(Collection<HTMaterialFlag> flags) {
        return this.flags.containsAll(flags);
    }

    // Object //

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

    // Registry //

    private static final Logger LOGGER = LogManager.getLogger("HTMaterial");

    private static final Map<String, HTMaterial> registry = new LinkedHashMap<>();

    public static Map<String, HTMaterial> getRegistry() {
        return ImmutableMap.copyOf(registry);
    }

    @NotNull
    public static Stream<HTMaterial> getMaterials() {
        return getRegistry().values().stream();
    }

    @NotNull
    public static Stream<HTMaterialKey> getMaterialKeys() {
        return getMaterials().map(HTMaterial::key);
    }

    @NotNull
    public static HTMaterial getMaterial(String name) {
        HTMaterial material = getMaterialOrNull(name);
        if (material == null) {
            throw new IllegalStateException("Material: " + name + " is not registered!");
        }
        return material;
    }

    @Nullable
    public static HTMaterial getMaterialOrNull(String name) {
        return registry.get(name);
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
                       HTMaterialPropertyMap properties,
                       HTMaterialFlagSet flags,
                       Color color,
                       String formula,
                       double molar) {
        var material = new HTMaterial(key, properties, flags, color, formula, molar);
        String name = key.name();
        if (registry.putIfAbsent(name, material) != null) {
            HTMaterial existMaterial = registry.get(name);
            throw new IllegalStateException("Name: " + name + " is already registered by " + existMaterial);
        }
        int index = key.index();
        if (indexRegistry.putIfAbsent(index, material) != null) {
            HTMaterial existMaterial = indexRegistry.get(index);
            throw new IllegalStateException("Index: " + index + " is already registered by " + existMaterial);
        }
        LOGGER.info("Material: " + key + " registered!");
    }
}
