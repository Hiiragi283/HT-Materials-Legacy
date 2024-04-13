package io.github.hiiragi283.htms.impl;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsPlugin;
import io.github.hiiragi283.htms.api.material.HTMaterialFlags;
import io.github.hiiragi283.htms.api.material.HTMaterialKeys;
import io.github.hiiragi283.htms.api.material.HTMaterialRegistry;
import io.github.hiiragi283.htms.api.material.HTMaterialType;
import io.github.hiiragi283.htms.api.material.content.HTMaterialContentRegistry;
import io.github.hiiragi283.htms.api.shape.HTShapeRegistry;
import io.github.hiiragi283.htms.api.shape.HTShapes;

public class HMDefaultPlugin implements HTMaterialsPlugin {

    @Override
    public @NotNull String getModId() {
        return HMConstants.MOD_ID;
    }

    @Override
    public int getPriority() {
        return -100;
    }

    @Override
    public void registerShape(HTShapeRegistry.@NotNull Builder builder) {
        HTMaterialsPlugin.super.registerShape(builder);
    }

    @Override
    public void registerMaterial(HTMaterialRegistry.@NotNull Builder builder) {
        builder.create(HTMaterialKeys.ALUMINUM, 13)
                .addFlag(HTMaterialFlags.GENERATE_INGOT)
                .setType(HTMaterialType.Metal.SHINY);
    }

    @Override
    public void registerMaterialContent(HTMaterialContentRegistry.@NotNull Builder builder) {
        builder.getItemBuilder(HTShapes.GEM)
                .setMaterialFilter(material -> material.type instanceof HTMaterialType.Gem &&
                        material.hasFlag(HTMaterialFlags.GENERATE_GEM));
        builder.getItemBuilder(HTShapes.INGOT)
                .setMaterialFilter(material -> !(material.type instanceof HTMaterialType.Gem) &&
                        material.hasFlag(HTMaterialFlags.GENERATE_INGOT));
    }
}
