package io.github.hiiragi283.api.shape;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

public final class HTShapeRegistry {

    public final ImmutableMap<HTShapeKey, HTShape> registry;

    public HTShapeRegistry(Map<HTShapeKey, HTShape> map) {
        registry = ImmutableMap.copyOf(map);
    }

    public Set<HTShapeKey> getKeys() {
        return registry.keySet();
    }

    public Collection<HTShape> getValues() {
        return registry.values();
    }

    @Nullable
    public HTShape getShape(HTShapeKey shapeKey) {
        return registry.get(shapeKey);
    }

    public boolean hasShape(HTShapeKey shapeKey) {
        return registry.containsKey(shapeKey);
    }
}
