package io.github.hiiragi283.material.api.material.property.fluid;

import java.awt.*;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.material.api.material.property.HTPropertyKey;
import io.github.hiiragi283.material.api.shape.HTShape;

public abstract class HTFluidPropertyBase<T extends HTFluidPropertyBase<T>> implements HTMaterialProperty<T> {

    private final HTPropertyKey<T> key;

    public HTFluidPropertyBase(HTPropertyKey<T> key) {
        this.key = key;
    }

    public int luminosity = 0;
    public int density = 1000;
    public int temperature = 300;
    public int viscosity = 1000;

    @NotNull
    public abstract String getFluidName(@NotNull HTMaterial material);

    @NotNull
    public abstract Color getFluidColor(@NotNull HTMaterial material);

    @NotNull
    public abstract String getTranslationKey(@NotNull HTMaterial material);

    public abstract boolean isGaseous();

    // HTMaterialProperty //

    @NotNull
    @Override
    public HTPropertyKey<T> getKey() {
        return key;
    }

    @Override
    public void verify(HTMaterial material) {}

    @Override
    public void addInformation(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack,
                               @NotNull List<String> tooltips) {
        Fluid fluid = FluidRegistry.getFluid(material.name());
        // Luminosity
        tooltips.add(I18n.format("tooltip.ht_materials.material.luminosity", fluid.getLuminosity()));
        // Density
        tooltips.add(I18n.format("tooltip.ht_materials.material.density", Math.abs(fluid.getDensity())));
        // Temperature
        tooltips.add(I18n.format("tooltip.ht_materials.material.temperature", fluid.getTemperature()));
        // Viscosity
        tooltips.add(I18n.format("tooltip.ht_materials.material.viscosity", fluid.getViscosity() / 1000.0));
        // Is Gaseous
        tooltips.add(I18n.format("tooltip.ht_materials.material.gaseous", fluid.isGaseous()));
    }
}
