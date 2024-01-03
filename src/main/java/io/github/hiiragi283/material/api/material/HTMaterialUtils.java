package io.github.hiiragi283.material.api.material;

import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public abstract class HTMaterialUtils {

    private HTMaterialUtils() {
    }

    public static Stream<HTMaterial> getMaterials(HTShapeKey shapeKey) {
        return HTMaterial.getRegistry().values().stream().filter(shapeKey.getShape());
    }

    public static Stream<HTMaterialKey> getMaterialKeys(HTShapeKey shapeKey) {
        return getMaterials(shapeKey).map(HTMaterial::getKey);
    }

    public static Stream<Integer> getMaterialIndexes(HTShapeKey shapeKey) {
        return getMaterials(shapeKey).map(HTMaterial::getIndex);
    }

    public static Stream<ItemStack> getMaterialStacks(HTMaterialItem item) {
        return getMaterialIndexes(item.getShapeKey()).map(index -> new ItemStack(item, 1, index));
    }

    public static void addInformation(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack, @NotNull List<String> tooltips) {
        //Title
        tooltips.add(I18n.format("tooltip.ht_materials.material.title"));
        //Name
        String name = shape == null ? material.getKey().getTranslatedName() : shape.key().getTranslatedName(material.getKey());
        tooltips.add(I18n.format("tooltip.ht_materials.material.name", name));
        //Formula
        String formula = material.getFormula();
        if (!formula.isEmpty()) tooltips.add(I18n.format("tooltip.ht_materials.material.formula", formula));
        //Molar Mass
        double molar = material.getMolar();
        if (molar > 0.0) tooltips.add(I18n.format("tooltip.ht_materials.material.molar", molar));
        //Tooltip from Properties
        material.getProperties().values().forEach(pro -> pro.addInformation(material, shape, stack, tooltips));
    }

}