package io.github.hiiragi283.api.material.flag;

import java.util.*;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.base.Preconditions;

import io.github.hiiragi283.api.material.HTMaterial;
import io.github.hiiragi283.api.material.property.HTPropertyKey;

public final class HTMaterialFlag {

    private final String name;
    private final Collection<HTMaterialFlag> requiredFlags;
    private final Collection<HTPropertyKey<?>> requiredProperties;

    private HTMaterialFlag(String name, Collection<HTMaterialFlag> requiredFlags,
                           Collection<HTPropertyKey<?>> requiredProperties) {
        this.name = name;
        this.requiredFlags = requiredFlags;
        this.requiredProperties = requiredProperties;
        registry.putIfAbsent(name, this);
    }

    public void verify(@NotNull HTMaterial material) {
        requiredProperties.forEach(key -> Preconditions.checkArgument(material.hasProperty(key),
                "Material; %s has not property; %s but required for; %s", material.key().name(), key.name(), name));
        requiredFlags.forEach(flag -> Preconditions.checkArgument(material.hasFlag(flag),
                "Material; %s has not flag; %s but required for; %s", material.key().name(), flag.name, name));
    }

    // Object

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj instanceof HTMaterialFlag otherFlag) return otherFlag.name.equals(this.name);
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    // Registry

    private static final Map<String, HTMaterialFlag> registry = new HashMap<>();

    @Nullable
    public static HTMaterialFlag getFlag(@NotNull String name) {
        return registry.get(name);
    }

    @NotNull
    public static HTMaterialFlag create(@NotNull String name) {
        return create(name, builder -> {});
    }

    @NotNull
    public static HTMaterialFlag create(@NotNull String name, Consumer<Builder> consumer) {
        var builder = new Builder(name);
        consumer.accept(builder);
        return builder.build();
    }

    // Builder

    public static final class Builder {

        private final String name;

        private Builder(String name) {
            this.name = name;
        }

        public final Set<@NotNull HTMaterialFlag> requiredFlags = new HashSet<>();

        public final Set<@NotNull HTPropertyKey<?>> requiredProperties = new HashSet<>();

        HTMaterialFlag build() {
            return new HTMaterialFlag(name, requiredFlags, requiredProperties);
        }
    }
}
