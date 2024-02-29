package io.github.hiiragi283.api.material;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.bsideup.jabel.Desugar;

import io.github.hiiragi283.api.material.composition.HTMaterialComposition;
import io.github.hiiragi283.api.material.element.HTElement;
import io.github.hiiragi283.api.material.flag.HTMaterialFlag;
import io.github.hiiragi283.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.api.material.property.HTPropertyKey;
import io.github.hiiragi283.api.shape.HTShape;

@Desugar
public record HTMaterial(HTMaterialKey key, int index, HTMaterialComposition composition, HTMaterialFlagSet flagSet,
                         HTMaterialPropertyMap propertyMap) {

    // Composition

    @NotNull
    public Map<HTElement, Integer> component() {
        return composition.component();
    }

    @NotNull
    public Color color() {
        return composition.color();
    }

    @NotNull
    public String formula() {
        return composition.formula();
    }

    public double molar() {
        return composition.molar();
    }

    // Flags

    public void forEachFlag(@NotNull Consumer<HTMaterialFlag> consumer) {
        flagSet.forEach(consumer);
    }

    public boolean hasFlag(@NotNull HTMaterialFlag flag) {
        return flagSet.contains(flag);
    }

    // Properties

    public void forEachProperty(@NotNull Consumer<HTMaterialProperty<?>> consumer) {
        propertyMap.values().forEach(consumer);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T extends HTMaterialProperty<T>> T getProperty(@NotNull HTPropertyKey<T> key) {
        HTMaterialProperty<?> property = propertyMap.get(key);
        return key.objClass().isInstance(property) ? (T) property : null;
    }

    public boolean hasProperty(@NotNull HTPropertyKey<?> key) {
        return propertyMap.contains(key);
    }

    // Other //

    public void addInformation(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack,
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
        material.propertyMap.values().forEach(pro -> pro.addInformation(material, shape, stack, tooltips));
    }
}
