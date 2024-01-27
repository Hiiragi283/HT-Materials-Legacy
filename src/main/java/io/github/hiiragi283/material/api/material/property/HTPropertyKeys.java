package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.property.component.HTCompoundProperty;
import io.github.hiiragi283.material.api.material.property.component.HTMixtureProperty;
import io.github.hiiragi283.material.api.material.property.fluid.HTFluidProperty;
import io.github.hiiragi283.material.api.material.property.fluid.HTGasProperty;

public abstract class HTPropertyKeys {

    private HTPropertyKeys() {}

    static void init() {}

    public static final HTPropertyKey<HTCompoundProperty> COMPOUND = new HTPropertyKey<>("compound",
            HTCompoundProperty.class);
    public static final HTPropertyKey<HTFluidProperty> FLUID = new HTPropertyKey<>("fluid", HTFluidProperty.class);
    public static final HTPropertyKey<HTGasProperty> GAS = new HTPropertyKey<>("gas", HTGasProperty.class);
    public static final HTPropertyKey<HTGemProperty> GEM = new HTPropertyKey<>("gem", HTGemProperty.class);
    public static final HTPropertyKey<HTMetalProperty> METAL = new HTPropertyKey<>("metal", HTMetalProperty.class);
    public static final HTPropertyKey<HTMixtureProperty> MIXTURE = new HTPropertyKey<>("mixture",
            HTMixtureProperty.class);
}
