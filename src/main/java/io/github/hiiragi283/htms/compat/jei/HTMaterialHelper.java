package io.github.hiiragi283.htms.compat.jei;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.material.HTMaterial;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.recipe.IIngredientType;

public enum HTMaterialHelper implements IIngredientHelper<@NotNull HTMaterial> {

    INSTANCE;

    @NotNull
    public static final IIngredientType<@NotNull HTMaterial> TYPE = () -> HTMaterial.class;

    @Nullable
    @Override
    public HTMaterial getMatch(@NotNull Iterable<@NotNull HTMaterial> ingredients,
                               @NotNull HTMaterial ingredientToMatch) {
        for (HTMaterial material : ingredients) {
            if (Objects.equals(material, ingredientToMatch)) {
                return material;
            }
        }
        return null;
    }

    @Override
    public @NotNull String getDisplayName(@NotNull HTMaterial ingredient) {
        return ingredient.key().getTranslatedName();
    }

    @Override
    public @NotNull String getUniqueId(@NotNull HTMaterial ingredient) {
        return getResourceId(ingredient);
    }

    @Override
    public @NotNull String getWildcardId(@NotNull HTMaterial ingredient) {
        return getUniqueId(ingredient);
    }

    @Override
    public @NotNull String getModId(@NotNull HTMaterial ingredient) {
        return HMConstants.MOD_ID;
    }

    @Override
    public @NotNull Iterable<@NotNull Color> getColors(@NotNull HTMaterial ingredient) {
        return Collections.singleton(ingredient.color);
    }

    @Override
    public @NotNull String getResourceId(@NotNull HTMaterial ingredient) {
        return ingredient.key().name();
    }

    @Override
    public @NotNull HTMaterial copyIngredient(@NotNull HTMaterial ingredient) {
        return ingredient;
    }

    @Override
    public boolean isValidIngredient(@NotNull HTMaterial ingredient) {
        return ingredient.isNotEmpty();
    }

    @Override
    public @NotNull Collection<@NotNull String> getOreDictNames(@NotNull HTMaterial ingredient) {
        return HTMaterialsAPI.INSTANCE.getShapeRegistry().values()
                .stream().map(shape -> shape.getOreDict(ingredient.key()))
                .collect(Collectors.toSet());
    }

    @Override
    public @NotNull String getErrorInfo(@Nullable HTMaterial ingredient) {
        return "";
    }
}
