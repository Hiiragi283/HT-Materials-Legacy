package io.github.hiiragi283.material.api.material.flag;

import java.util.*;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.property.HTPropertyKey;

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

    @GroovyBlacklist
    void verify(HTMaterial material) {
        requiredProperties.forEach(key -> {
            if (!material.hasProperty(key)) {
                throw new IllegalStateException(
                        "The material: $material has no property: ${key.name} but required for ${this.name}!");
            }
        });
        requiredFlags.forEach(flag -> {
            if (!material.hasFlag(flag)) {
                throw new IllegalStateException(
                        "The material: $material has no flag: ${flag.name} but required for ${this.name}!");
            }
        });
    }

    // Object //

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

    // Registry //

    private static final Map<String, HTMaterialFlag> registry = new HashMap<>();

    @Nullable
    public static HTMaterialFlag getFlag(String name) {
        return registry.get(name);
    }

    public static HTMaterialFlag create(String name) {
        return create(name, builder -> {});
    }

    public static HTMaterialFlag create(String name, Consumer<Builder> consumer) {
        var builder = new Builder(name);
        consumer.accept(builder);
        return builder.build();
    }

    static {
        HTMaterialFlags.init();
    }

    // Builder //

    public static final class Builder {

        private final String name;

        private Builder(String name) {
            this.name = name;
        }

        public final Set<HTMaterialFlag> requiredFlags = new HashSet<>();

        public final Set<HTPropertyKey<?>> requiredProperties = new HashSet<>();

        HTMaterialFlag build() {
            return new HTMaterialFlag(name, requiredFlags, requiredProperties);
        }
    }
}
