package io.github.hiiragi283.api.material.flag;

import io.github.hiiragi283.api.material.property.HTGemProperty;

public final class HTMaterialFlags {

    public static final HTMaterialFlag GENERATE_DUST = HTMaterialFlag.create("generate_dust");
    public static final HTMaterialFlag GENERATE_GEAR = HTMaterialFlag.create("generate_gear");
    public static final HTMaterialFlag GENERATE_GEM = HTMaterialFlag.create(
            "generate_gem",
            builder -> builder.requiredProperties.add(HTGemProperty.KEY));
    public static final HTMaterialFlag GENERATE_INGOT = HTMaterialFlag.create("generate_ingot");
    public static final HTMaterialFlag GENERATE_NUGGET = HTMaterialFlag.create("generate_nugget");
    public static final HTMaterialFlag GENERATE_PLATE = HTMaterialFlag.create("generate_plate");
    public static final HTMaterialFlag GENERATE_STICK = HTMaterialFlag.create("generate_stick");

    private HTMaterialFlags() {}
}