package io.github.hiiragi283.api.material;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

public final class HTMaterialRegistry {

    public final ImmutableMap<HTMaterialKey, HTMaterial> nameRegistry;
    public final ImmutableMap<Integer, HTMaterial> indexRegistry;

    public HTMaterialRegistry(Map<HTMaterialKey, HTMaterial> nameRegistry, Map<Integer, HTMaterial> indexRegistry) {
        this.nameRegistry = ImmutableMap.copyOf(nameRegistry);
        this.indexRegistry = ImmutableMap.copyOf(indexRegistry);
    }

    public Set<HTMaterialKey> getKeys() {
        return nameRegistry.keySet();
    }

    public Set<Integer> getIndexes() {
        return indexRegistry.keySet();
    }

    public Collection<HTMaterial> getValues() {
        return nameRegistry.values();
    }

    @Nullable
    public HTMaterial getMaterial(HTMaterialKey materialKey) {
        return nameRegistry.get(materialKey);
    }

    @Nullable
    public HTMaterial getMaterial(int index) {
        return indexRegistry.get(index);
    }

    public boolean hasMaterial(HTMaterialKey materialKey) {
        return nameRegistry.containsKey(materialKey);
    }
}
