package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.HTMaterial;
import org.jetbrains.annotations.NotNull;

public enum HTMetalProperty implements HTMaterialProperty<HTMetalProperty> {
    INSTANCE;

    @NotNull
    @Override
    public HTPropertyKey<HTMetalProperty> getKey() {
        return HTPropertyKeys.METAL;
    }

    @Override
    public void verify(HTMaterial material) {
        if (material.hasProperty(HTPropertyKeys.GEM)) {
            throw new IllegalStateException("Material: " + material.key() + " has both Metal and Gem Property, which is not allowed!");
        }
    }
}