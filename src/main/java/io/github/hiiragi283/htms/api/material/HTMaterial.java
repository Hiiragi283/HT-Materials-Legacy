package io.github.hiiragi283.htms.api.material;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.bsideup.jabel.Desugar;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import io.github.hiiragi283.htms.api.extension.TypedResourceLocation;
import io.github.hiiragi283.htms.api.material.composition.HTElement;
import io.github.hiiragi283.htms.api.material.composition.HTMaterialComposition;
import io.github.hiiragi283.htms.api.material.property.HTMaterialProperty;
import io.github.hiiragi283.htms.api.shape.HTShape;

public final class HTMaterial {

    @NotNull
    private final HTMaterialKey key;

    private final int index;

    @NotNull
    private final ImmutableSet<@NotNull ResourceLocation> flagSet;

    @NotNull
    private final ImmutableMap<@NotNull TypedResourceLocation<?>, @NotNull HTMaterialProperty<?>> propertyMap;

    @NotNull
    public final HTMaterialType type;

    @NotNull
    public final ImmutableMap<HTElement, Integer> componentMap;

    @NotNull
    public final Color color;

    @NotNull
    public final String formula;

    public final double molar;

    HTMaterial(@NotNull Map.Entry<@NotNull HTMaterialKey, HTMaterial.@NotNull Builder> entry) {
        this.key = entry.getKey();
        HTMaterial.Builder builder = entry.getValue();
        this.index = builder.index;
        this.flagSet = ImmutableSet.copyOf(builder.flagSet);
        this.propertyMap = ImmutableMap.copyOf(builder.propertyMap);
        this.type = builder.type;
        HTMaterialComposition composition = builder.composition;
        this.componentMap = composition.componentMap();
        this.color = composition.color();
        this.formula = composition.formula();
        this.molar = composition.molar();
    }

    public HTMaterialKey key() {
        return key;
    }

    public int index() {
        return index;
    }

    // Flag //

    public boolean hasFlag(@NotNull ResourceLocation flag) {
        return flagSet.contains(flag);
    }

    public void forEachFlag(@NotNull Consumer<@NotNull ResourceLocation> action) {
        flagSet.forEach(action);
    }

    // Property //

    public <T extends HTMaterialProperty<T>> @NotNull Optional<T> getPropertyOptional(@NotNull TypedResourceLocation<T> key) {
        return Optional.ofNullable(getProperty(key));
    }

    public <T extends HTMaterialProperty<T>> @Nullable T getProperty(@NotNull TypedResourceLocation<T> key) {
        return key.cast(propertyMap.get(key));
    }

    public boolean hasProperty(@NotNull TypedResourceLocation<?> property) {
        return propertyMap.containsKey(property);
    }

    public void forEachProperty(@NotNull Consumer<@NotNull HTMaterialProperty<?>> action) {
        propertyMap.values().forEach(action);
    }

    // Type //

    public @Nullable HTShape getDefaultShape() {
        return type.getDefaultShape();
    }

    // Object //

    @Override
    public String toString() {
        return key().name();
    }

    // Builder //

    public static final class Builder {

        final int index;

        @NotNull
        HTMaterialComposition composition = HTMaterialComposition.Empty.INSTANCE;

        @NotNull
        Set<@NotNull ResourceLocation> flagSet = new HashSet<>();

        @NotNull
        Map<@NotNull TypedResourceLocation<?>, @NotNull HTMaterialProperty<?>> propertyMap = new HashMap<>();

        @NotNull
        HTMaterialType type = HTMaterialType.Undefined.INSTANCE;

        Builder(int index) {
            this.index = index;
        }

        public Builder setComposition(@NotNull HTMaterialComposition composition) {
            this.composition = composition;
            return this;
        }

        public Builder addFlag(@NotNull ResourceLocation location) {
            flagSet.add(location);
            return this;
        }

        public Builder removeFlag(@NotNull ResourceLocation location) {
            flagSet.remove(location);
            return this;
        }

        public Builder addProperty(@NotNull HTMaterialProperty<?> property) {
            propertyMap.put(property.getId(), property);
            return this;
        }

        public Builder removeProperty(@NotNull TypedResourceLocation<?> key) {
            propertyMap.remove(key);
            return this;
        }

        public Builder setType(@NotNull HTMaterialType type) {
            this.type = type;
            return this;
        }
    }

    // Tooltip //

    public static void addInformation(TooltipContext context) {
        HTMaterial material = context.material();
        HTShape shape = context.shape();
        List<String> tooltips = context.tooltips();
        // Title
        tooltips.add(I18n.format("tooltip.ht_materials.material.title"));
        // Name
        String name = shape != null ? shape.getTranslatedName(material.key()) : material.key().getTranslatedName();
        tooltips.add(I18n.format("tooltip.ht_materials.material.name", name));
        // Type
        tooltips.add(
                I18n.format("tooltip.ht_materials.material.type", material.type.getTranslatedName()));
        // Formula
        String formula = material.formula;
        if (!formula.isEmpty()) {
            tooltips.add(I18n.format("tooltip.ht_materials.material.formula", formula));
        }
        // Molar Mass
        double molar = material.molar;
        if (molar > 0.0) {
            tooltips.add(I18n.format("tooltip.ht_materials.material.molar", molar));
        }
        // Tooltip from Properties
        material.forEachProperty(property -> property.addInformation(context));
    }

    @Desugar
    public record TooltipContext(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack,
                                 @NotNull List<String> tooltips) {}
}
