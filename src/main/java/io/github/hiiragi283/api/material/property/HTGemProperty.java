package io.github.hiiragi283.api.material.property;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.api.material.HTMaterial;

public enum HTGemProperty implements HTMaterialProperty<HTGemProperty> {

    DIAMOND,
    EMERALD;

    public static final HTPropertyKey<HTGemProperty> KEY = new HTPropertyKey<>("gem", HTGemProperty.class);

    @NotNull
    @Override
    public HTPropertyKey<HTGemProperty> getKey() {
        return KEY;
    }

    @Override
    public void verify(HTMaterial material) {}
}
