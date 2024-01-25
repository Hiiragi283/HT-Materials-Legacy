package io.github.hiiragi283.material.api.material;

import java.util.List;
import java.util.stream.Stream;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.material.api.part.HTPartDictionary;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;

public abstract class HTMaterialUtils {

    private HTMaterialUtils() {}

    public static Stream<ItemStack> getAllStacks() {
        return HTShape.getShapeKeys().flatMap(HTMaterialUtils::getMatchingStacks);
    }

    public static Stream<ItemStack> getMatchingStacks(HTMaterialKey materialKey) {
        return getMatchingStacks(materialKey, 1);
    }

    public static Stream<ItemStack> getMatchingStacks(HTMaterialKey materialKey, int count) {
        return HTShape.getShapeKeys().flatMap(key -> HTPartDictionary.getItemStacks(key, materialKey, count));
    }

    public static Stream<ItemStack> getMatchingStacks(HTShapeKey shapeKey) {
        return getMatchingStacks(shapeKey, 1);
    }

    public static Stream<ItemStack> getMatchingStacks(HTShapeKey shapeKey, int count) {
        return HTMaterial.getMaterialKeys().flatMap(key -> HTPartDictionary.getItemStacks(shapeKey, key, count));
    }

    public static void addInformation(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack,
                                      @NotNull List<String> tooltips) {
        // Title
        tooltips.add(I18n.format("tooltip.ht_materials.material.title"));
        // Name
        String name = shape == null ? material.key().getTranslatedName() :
                shape.key().getTranslatedName(material.key());
        tooltips.add(I18n.format("tooltip.ht_materials.material.name", name));
        // Formula
        String formula = material.formula();
        if (!formula.isEmpty()) tooltips.add(I18n.format("tooltip.ht_materials.material.formula", formula));
        // Molar Mass
        double molar = material.molar();
        if (molar > 0.0) tooltips.add(I18n.format("tooltip.ht_materials.material.molar", molar));
        // Tooltip from Properties
        material.getProperties().values().forEach(pro -> pro.addInformation(material, shape, stack, tooltips));
    }
}
