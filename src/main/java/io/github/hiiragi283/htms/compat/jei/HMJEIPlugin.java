package io.github.hiiragi283.htms.compat.jei;

import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public final class HMJEIPlugin implements IModPlugin {

    @NotNull
    public static final String MATERIAL = HMConstants.MOD_ID + ".material";

    @Override
    public void registerIngredients(@NotNull IModIngredientRegistration registry) {
        registry.register(
                HTMaterialHelper.TYPE,
                HTMaterialsAPI.INSTANCE.getMaterialRegistry().values(),
                HTMaterialHelper.INSTANCE,
                HTMaterialRenderer.INSTANCE);
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new HTMaterialCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(@NotNull IModRegistry registry) {
        // Material
        registry.handleRecipes(HTMaterialCategory.Wrapper.class, wrapper -> wrapper, MATERIAL);
        registry.addRecipes(
                HTMaterialsAPI.INSTANCE.getMaterialRegistry().values()
                        .stream()
                        .map(HTMaterialCategory.Wrapper::new)
                        .collect(Collectors.toList()),
                MATERIAL);
        registry.addRecipeCatalyst(new ItemStack(HTMaterialsAPI.INSTANCE.getDictionaryItem()), MATERIAL);
    }
}