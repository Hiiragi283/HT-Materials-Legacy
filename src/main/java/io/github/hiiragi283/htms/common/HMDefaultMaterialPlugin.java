package io.github.hiiragi283.htms.common;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsPlugin;
import io.github.hiiragi283.htms.api.extension.HTColor;
import io.github.hiiragi283.htms.api.extension.HTColorUtil;
import io.github.hiiragi283.htms.api.material.HTMaterialFlags;
import io.github.hiiragi283.htms.api.material.HTMaterialKeys;
import io.github.hiiragi283.htms.api.material.HTMaterialRegistry;
import io.github.hiiragi283.htms.api.material.HTMaterialType;
import io.github.hiiragi283.htms.api.material.composition.HTElements;
import io.github.hiiragi283.htms.api.material.composition.HTMaterialComposition;
import io.github.hiiragi283.htms.api.material.content.HTMaterialContentRegistry;
import io.github.hiiragi283.htms.api.shape.HTShapeRegistry;
import io.github.hiiragi283.htms.api.shape.HTShapes;

public final class HMDefaultMaterialPlugin implements HTMaterialsPlugin {

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
        builder.add(HTShapes.BLOCK);
        builder.add(HTShapes.ORE);
        builder.add(HTShapes.DUST);
        builder.add(HTShapes.GEAR);
        builder.add(HTShapes.GEM);
        builder.add(HTShapes.INGOT);
        builder.add(HTShapes.NUGGET);
        builder.add(HTShapes.PLATE);
        builder.add(HTShapes.ROD);
    }

    @Override
    public void registerMaterial(HTMaterialRegistry.@NotNull Builder builder) {
        // 1st Period
        builder.addSimpleMaterial(HTMaterialKeys.HYDROGEN, 1, HTElements.H, 2, null);
        builder.addSimpleMaterial(HTMaterialKeys.HELIUM, 2, HTElements.He, 1, null);
        // 2nd Period
        builder.addMetalMaterial(HTMaterialKeys.LITHIUM, 3, HTElements.Li, HTMaterialType.Metal.SHINY, false);
        builder.addMetalMaterial(HTMaterialKeys.BERYLLIUM, 4, HTElements.Be, HTMaterialType.Metal.DULL, false);
        builder.addSimpleMaterial(HTMaterialKeys.CARBON, 6, HTElements.C, 1, null)
                .addFlag(HTMaterialFlags.GENERATE_DUST)
                .addFlag(HTMaterialFlags.GENERATE_PLATE);
        builder.addSimpleMaterial(HTMaterialKeys.NITROGEN, 7, HTElements.N, 2, null);
        builder.addSimpleMaterial(HTMaterialKeys.OXYGEN, 8, HTElements.O, 2, null);
        builder.addSimpleMaterial(HTMaterialKeys.FLUORINE, 9, HTElements.F, 2, null);
        // 3rd Period
        builder.addMetalMaterial(HTMaterialKeys.SODIUM, 11, HTElements.Na, HTMaterialType.Metal.DULL, false);
        builder.addMetalMaterial(HTMaterialKeys.MAGNESIUM, 12, HTElements.Mg, HTMaterialType.Metal.DULL, false);
        builder.addMetalMaterial(HTMaterialKeys.ALUMINUM, 13, HTElements.Al, HTMaterialType.Metal.SHINY, true);
        // builder.addAlternativeName(HTMaterialKeys.ALUMINUM, "aluminium");
        builder.addMetalMaterial(HTMaterialKeys.SILICON, 14, HTElements.Si, HTMaterialType.Metal.DULL, false);
        builder.addSimpleMaterial(HTMaterialKeys.PHOSPHORUS, 15, HTElements.P, 1, null)
                .addFlag(HTMaterialFlags.GENERATE_DUST);
        builder.addSimpleMaterial(HTMaterialKeys.SULFUR, 16, HTElements.S, 8, null)
                .addFlag(HTMaterialFlags.GENERATE_DUST);
        builder.addSimpleMaterial(HTMaterialKeys.CHLORINE, 17, HTElements.Cl, 2, null);
        // 4th Period
        builder.addMetalMaterial(HTMaterialKeys.POTASSIUM, 19, HTElements.K, HTMaterialType.Metal.DULL, false);
        builder.addMetalMaterial(HTMaterialKeys.CALCIUM, 20, HTElements.Ca, HTMaterialType.Metal.DULL, false);
        builder.addMetalMaterial(HTMaterialKeys.TITANIUM, 22, HTElements.Ti, HTMaterialType.Metal.SHINY, true);
        builder.addMetalMaterial(HTMaterialKeys.CHROMIUM, 24, HTElements.Cr, HTMaterialType.Metal.SHINY, false);
        // builder.addAlternativeName(HTMaterialKeys.CHROMIUM, "chrome");
        builder.addMetalMaterial(HTMaterialKeys.MANGANESE, 25, HTElements.Mn, HTMaterialType.Metal.DULL, false);
        builder.addMetalMaterial(HTMaterialKeys.IRON, 26, HTElements.Fe, HTMaterialType.Metal.SHINY, true)
                .removeFlag(HTMaterialFlags.GENERATE_INGOT)
                .removeFlag(HTMaterialFlags.GENERATE_NUGGET);
        builder.addMetalMaterial(HTMaterialKeys.COBALT, 27, HTElements.Co, HTMaterialType.Metal.SHINY, false);
        builder.addMetalMaterial(HTMaterialKeys.NICKEL, 28, HTElements.Ni, HTMaterialType.Metal.SHINY, true);
        builder.addMetalMaterial(HTMaterialKeys.COPPER, 29, HTElements.Cu, HTMaterialType.Metal.SHINY, true);
        builder.addMetalMaterial(HTMaterialKeys.ZINC, 30, HTElements.Zn, HTMaterialType.Metal.SHINY, false);
        // 5th Period
        builder.addMetalMaterial(HTMaterialKeys.SILVER, 47, HTElements.Ag, HTMaterialType.Metal.SHINY, true);
        builder.addMetalMaterial(HTMaterialKeys.TIN, 50, HTElements.Sn, HTMaterialType.Metal.DULL, true);

        // 6th Period
        builder.addMetalMaterial(HTMaterialKeys.TUNGSTEN, 74, HTElements.W, HTMaterialType.Metal.DULL, true);
        builder.addMetalMaterial(HTMaterialKeys.IRIDIUM, 77, HTElements.Ir, HTMaterialType.Metal.SHINY, true);
        builder.addMetalMaterial(HTMaterialKeys.PLATINUM, 78, HTElements.Pt, HTMaterialType.Metal.SHINY, true);
        builder.addMetalMaterial(HTMaterialKeys.GOLD, 79, HTElements.Au, HTMaterialType.Metal.SHINY, true)
                .removeFlag(HTMaterialFlags.GENERATE_INGOT)
                .removeFlag(HTMaterialFlags.GENERATE_NUGGET);
        builder.addSimpleMaterial(HTMaterialKeys.MERCURY, 80, HTElements.Hg, 1, null);
        builder.addMetalMaterial(HTMaterialKeys.LEAD, 82, HTElements.Pb, HTMaterialType.Metal.DULL, true);
        // 7th Period
        builder.addMetalMaterial(HTMaterialKeys.URANIUM, 92, HTElements.U, HTMaterialType.Metal.DULL, false);
        builder.addMetalMaterial(HTMaterialKeys.PLUTONIUM, 94, HTElements.Pu, HTMaterialType.Metal.DULL, false);
        // Vanilla - Fluids
        builder.getOrCreate(HTMaterialKeys.WATER, 200)
                .setComposition(
                        HTMaterialComposition.molecular(
                                ImmutableMap.of(HTElements.H, 2, HTElements.O, 1),
                                builderIn -> builderIn.setColor(HTColor.BLUE)));
        builder.getOrCreate(HTMaterialKeys.LAVA, 201)
                .setComposition(
                        HTMaterialComposition.molecular(
                                ImmutableMap.of(HTElements.SiO2, 1),
                                builderIn -> builderIn
                                        .setColor(HTColorUtil.averageColor(HTColor.DARK_RED, HTColor.GOLD))));
        builder.getOrCreate(HTMaterialKeys.MILK, 202)
                .setComposition(
                        HTMaterialComposition.mixture(ImmutableList.of(HTElements.C, HTElements.H, HTElements.O)));
    }

    @Override
    public void registerMaterialContent(HTMaterialContentRegistry.@NotNull Builder builder) {
        builder.getItemBuilder(HTShapes.DUST)
                .setMaterialFilter(material -> material.hasFlag(HTMaterialFlags.GENERATE_DUST));
        builder.getItemBuilder(HTShapes.GEAR)
                .setMaterialFilter(material -> material.hasFlag(HTMaterialFlags.GENERATE_GEAR));
        builder.getItemBuilder(HTShapes.GEM)
                .setMaterialFilter(material -> material.type instanceof HTMaterialType.Gem &&
                        material.hasFlag(HTMaterialFlags.GENERATE_GEM));
        builder.getItemBuilder(HTShapes.INGOT)
                .setMaterialFilter(material -> !(material.type instanceof HTMaterialType.Gem) &&
                        material.hasFlag(HTMaterialFlags.GENERATE_INGOT));
        builder.getItemBuilder(HTShapes.NUGGET)
                .setMaterialFilter(material -> !(material.type instanceof HTMaterialType.Gem) &&
                        material.hasFlag(HTMaterialFlags.GENERATE_NUGGET));
        builder.getItemBuilder(HTShapes.PLATE)
                .setMaterialFilter(material -> material.hasFlag(HTMaterialFlags.GENERATE_PLATE));
        builder.getItemBuilder(HTShapes.ROD)
                .setMaterialFilter(material -> material.hasFlag(HTMaterialFlags.GENERATE_ROD));
    }
}
