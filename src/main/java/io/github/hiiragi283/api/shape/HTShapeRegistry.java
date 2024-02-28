package io.github.hiiragi283.api.shape;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

public final class HTShapeRegistry {

    public final ImmutableMap<HTShapeKey, HTShape> registry;

    public HTShapeRegistry(Map<HTShapeKey, HTShape> map) {
        registry = ImmutableMap.copyOf(map);
    }

    @NotNull
    public Set<HTShapeKey> getKeys() {
        return registry.keySet();
    }

    @NotNull
    public Collection<HTShape> getValues() {
        return registry.values();
    }

    @Nullable
    public HTShape getShape(@NotNull HTShapeKey shapeKey) {
        return registry.get(shapeKey);
    }

    public boolean hasShape(@NotNull HTShapeKey shapeKey) {
        return registry.containsKey(shapeKey);
    }
}
