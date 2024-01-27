package io.github.hiiragi283.material.api.material.property.fluid;

import java.awt.*;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.property.HTPropertyKeys;

public class HTGasProperty extends HTFluidPropertyBase<HTGasProperty> {

    public HTGasProperty() {
        super(HTPropertyKeys.GAS);
        this.density *= -1;
    }

    @NotNull
    @Override
    public String getFluidName(@NotNull HTMaterial material) {
        return material.name();
    }

    @NotNull
    @Override
    public Color getFluidColor(@NotNull HTMaterial material) {
        return material.color();
    }

    @NotNull
    @Override
    public String getTranslationKey(@NotNull HTMaterial material) {
        return material.key().getTranslationKey();
    }

    @Override
    public boolean isGaseous() {
        return true;
    }
}
