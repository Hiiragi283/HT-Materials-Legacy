package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.HTMaterial;
import org.jetbrains.annotations.NotNull;

public class HTMetalProperty implements HTMaterialProperty<HTMetalProperty> {

    public static final HTMetalProperty INSTANCE = new HTMetalProperty();

    private HTMetalProperty() {
    }

    @NotNull
    @Override
    public HTPropertyKey<HTMetalProperty> getKey() {
        return HTPropertyKeys.METAL;
    }

    @Override
    public void verify(HTMaterial material) {
        if (material.hasProperty(HTPropertyKeys.GEM)) {
            throw new IllegalStateException("Material: " + material.getKey() + " has both Metal and Gem Property, which is not allowed!");
        }
    }
}