package io.github.hiiragi283.material.api.fluid;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.property.fluid.HTFluidPropertyBase;

public final class HTMaterialFluid extends Fluid {

    public static ResourceLocation getStillLocation() {
        return new ResourceLocation("blocks/concrete_white");
    }

    public static ResourceLocation getFlowingLocation() {
        return new ResourceLocation("blocks/concrete_white");
    }

    private final SoundEvent emptySound;

    private final SoundEvent fillSound;

    public HTMaterialFluid(HTMaterial material, HTFluidPropertyBase<?> fluidProperty) {
        super(fluidProperty.getFluidName(material), getStillLocation(), getFlowingLocation(), null,
                fluidProperty.getFluidColor(material).getRGB());
        luminosity = fluidProperty.luminosity;
        density = fluidProperty.density;
        temperature = fluidProperty.temperature;
        viscosity = fluidProperty.viscosity;
        isGaseous = fluidProperty.isGaseous();
        emptySound = temperature > 300 ? SoundEvents.ITEM_BUCKET_EMPTY_LAVA : SoundEvents.ITEM_BUCKET_EMPTY;
        fillSound = temperature > 300 ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL;
        unlocalizedName = fluidProperty.getTranslationKey(material);
    }

    @Override
    public SoundEvent getEmptySound() {
        return emptySound;
    }

    @Override
    public SoundEvent getFillSound() {
        return fillSound;
    }

    @Override
    public String getUnlocalizedName() {
        return unlocalizedName;
    }
}
