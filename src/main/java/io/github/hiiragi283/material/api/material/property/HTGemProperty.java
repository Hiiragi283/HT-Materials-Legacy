package io.github.hiiragi283.material.api.material.property;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.material.api.material.HTMaterial;

public enum HTGemProperty implements HTMaterialProperty<HTGemProperty> {

    AMETHYST,
    COAL,
    CUBIC,
    DIAMOND,
    EMERALD,
    LAPIS,
    QUARTZ,
    RUBY;

    @NotNull
    @Override
    public HTPropertyKey<HTGemProperty> getKey() {
        return HTPropertyKeys.GEM;
    }

    @Override
    public void verify(HTMaterial material) {
        if (material.hasProperty(HTPropertyKeys.METAL)) {
            throw new IllegalStateException(
                    "Material: " + material.key() + " has both Metal and Gem Property, which is not allowed!");
        }
    }
}
