package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.shape.HTShape;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class HTFluidProperty implements HTMaterialProperty<HTFluidProperty> {

    public int luminosity = 0;

    public int density = 1000;

    public int temperature = 300;

    public int viscosity = 1000;

    public boolean isGaseous = false;

    //    HTMaterialProperty    //

    @NotNull
    @Override
    public HTPropertyKey<HTFluidProperty> getKey() {
        return HTPropertyKeys.FLUID;
    }

    @Override
    public void verify(HTMaterial material) {
        if (!FluidRegistry.isFluidRegistered(material.name())) {
            throw new IllegalStateException("");
        }
    }

    @Override
    public void addInformation(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack, @NotNull List<String> tooltips) {
        Fluid fluid = FluidRegistry.getFluid(material.name());
        //Luminosity
        tooltips.add(I18n.format("", fluid.getLuminosity()));
        //Density
        tooltips.add(I18n.format("", fluid.getDensity()));
        //Temperature
        tooltips.add(I18n.format("", fluid.getTemperature()));
        //Viscosity
        tooltips.add(I18n.format("", fluid.getViscosity()));
        //Is Gaseous
        tooltips.add(I18n.format("", fluid.isGaseous()));
    }

}