package io.github.hiiragi283.material.api.material.flag;

import io.github.hiiragi283.material.api.material.property.HTPropertyKeys;

public abstract class HTMaterialFlags {

    private HTMaterialFlags() {}

    static void init() {}

    public static final HTMaterialFlag GENERATE_BLOCk = HTMaterialFlag.create("generate_block");
    public static final HTMaterialFlag GENERATE_DUST = HTMaterialFlag.create("generate_dust");
    public static final HTMaterialFlag GENERATE_GEAR = HTMaterialFlag.create("generate_gear");
    public static final HTMaterialFlag GENERATE_GEM = HTMaterialFlag.create("generate_gem",
            builder -> builder.requiredProperties.add(HTPropertyKeys.GEM));
    public static final HTMaterialFlag GENERATE_INGOT = HTMaterialFlag.create("generate_ingot");
    public static final HTMaterialFlag GENERATE_NUGGET = HTMaterialFlag.create("generate_nugget",
            builder -> builder.requiredProperties.add(HTPropertyKeys.METAL));
    public static final HTMaterialFlag GENERATE_PLATE = HTMaterialFlag.create("generate_plate");
    public static final HTMaterialFlag GENERATE_STICK = HTMaterialFlag.create("generate_stick");
}
