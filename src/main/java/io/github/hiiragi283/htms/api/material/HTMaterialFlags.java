package io.github.hiiragi283.htms.api.material;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;

public final class HTMaterialFlags {

    private HTMaterialFlags() {}

    @NotNull
    public static final ResourceLocation GENERATE_DUST = HTMaterialsAPI.getId("generate_dust");

    @NotNull
    public static final ResourceLocation GENERATE_GEAR = HTMaterialsAPI.getId("generate_gear");

    @NotNull
    public static final ResourceLocation GENERATE_GEM = HTMaterialsAPI.getId("generate_gem");

    @NotNull
    public static final ResourceLocation GENERATE_INGOT = HTMaterialsAPI.getId("generate_ingot");

    @NotNull
    public static final ResourceLocation GENERATE_NUGGET = HTMaterialsAPI.getId("generate_nugget");

    @NotNull
    public static final ResourceLocation GENERATE_PLATE = HTMaterialsAPI.getId("generate_plate");

    @NotNull
    public static final ResourceLocation GENERATE_ROD = HTMaterialsAPI.getId("generate_rod");
}
