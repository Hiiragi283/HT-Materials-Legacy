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
import io.github.hiiragi283.htms.api.material.property.HTTooltipProperty;
import io.github.hiiragi283.htms.api.shape.HTShape;

public final class HTMaterial {

    @NotNull
    private final HTMaterialKey key;

    private final int index;

    @NotNull
    private final ImmutableSet<@NotNull ResourceLocation> flagSet;

    @NotNull
    private final ImmutableMap<@NotNull TypedResourceLocation<?>, @NotNull Object> propertyMap;

    @NotNull
    public final HTMaterialType type;

    @NotNull
    public final ImmutableMap<HTElement, Integer> componentMap;

    @NotNull
    public final Color color;

    @NotNull
    public final String formula;

    public final double molar;

    private boolean isEmpty;

    static HTMaterial empty(@NotNull HTMaterialKey key) {
        HTMaterial emptyMaterial = new HTMaterial(key, new HTMaterial.Builder(-1));
        emptyMaterial.isEmpty = true;
        return emptyMaterial;
    }

    HTMaterial(@NotNull Map.Entry<@NotNull HTMaterialKey, HTMaterial.@NotNull Builder> entry) {
        this(entry.getKey(), entry.getValue());
    }

    private HTMaterial(@NotNull HTMaterialKey key, HTMaterial.@NotNull Builder builder) {
        this.key = key;
        this.index = builder.index;
        this.flagSet = ImmutableSet.copyOf(builder.flagSet);
        this.propertyMap = ImmutableMap.copyOf(builder.propertyMap);
        this.type = builder.type;
        HTMaterialComposition composition = builder.composition;
        this.componentMap = composition.componentMap();
        this.color = composition.color();
        this.formula = composition.formula();
        this.molar = composition.molar();
        this.isEmpty = false;
    }

    public HTMaterialKey key() {
        return key;
    }

    public int index() {
        return index;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isNotEmpty() {
        return !isEmpty;
    }

    // Flag //

    public boolean hasFlag(@NotNull ResourceLocation flag) {
        return flagSet.contains(flag);
    }

    public void forEachFlag(@NotNull Consumer<@NotNull ResourceLocation> action) {
        flagSet.forEach(action);
    }

    // Property //

    public <T> @NotNull Optional<T> getPropertyOptional(@NotNull TypedResourceLocation<T> key) {
        return Optional.ofNullable(getProperty(key));
    }

    public <T> @Nullable T getProperty(@NotNull TypedResourceLocation<T> key) {
        return key.cast(propertyMap.get(key));
    }

    public boolean hasProperty(@NotNull TypedResourceLocation<?> property) {
        return propertyMap.containsKey(property);
    }

    // Type //

    public @Nullable HTShape getDefaultShape() {
        return type.getDefaultShape();
    }

    // Object //

    @Override
    public boolean equals(Object obj) {
        return obj instanceof HTMaterial other && Objects.equals(this.key, other.key) &&
                Objects.equals(this.index, other.index);
    }

    @Override
    public int hashCode() {
        int hashcode = key.hashCode();
        hashcode += index;
        return hashcode;
    }

    @Override
    public String toString() {
        return key().name();
    }

    // Builder //

    public static final class Builder {

        final int index;

        @NotNull
        HTMaterialComposition composition = HTMaterialComposition.empty();

        @NotNull
        Set<@NotNull ResourceLocation> flagSet = new HashSet<>();

        @NotNull
        Map<@NotNull TypedResourceLocation<?>, @NotNull Object> propertyMap = new HashMap<>();

        @NotNull
        HTMaterialType type = HTMaterialType.Undefined.INSTANCE;

        Builder(int index) {
            this.index = index;
        }

        public Builder setComposition(@Nullable HTMaterialComposition composition) {
            if (composition != null) {
                this.composition = composition;
            }
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

        public <T> Builder addProperty(@Nullable TypedResourceLocation<T> key, @Nullable T value) {
            if (key != null && value != null) {
                propertyMap.put(key, value);
            }
            return this;
        }

        public Builder removeProperty(@NotNull TypedResourceLocation<?> key) {
            propertyMap.remove(key);
            return this;
        }

        public Builder setType(@Nullable HTMaterialType type) {
            if (type != null) {
                this.type = type;
            }
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
        material.propertyMap.values().stream()
                .filter(value -> value instanceof HTTooltipProperty)
                .map(value -> (HTTooltipProperty) value)
                .forEach(property -> property.addInformation(context));
    }

    @Desugar
    public record TooltipContext(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack,
                                 @NotNull List<String> tooltips) {}
}
