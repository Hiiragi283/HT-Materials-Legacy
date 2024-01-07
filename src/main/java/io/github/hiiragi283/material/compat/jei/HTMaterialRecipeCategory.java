package io.github.hiiragi283.material.compat.jei;

import io.github.hiiragi283.material.HMReference;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class HTMaterialRecipeCategory implements IRecipeCategory<HTMaterialRecipeWrapper> {

    private final IDrawable background;

    public HTMaterialRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(new ResourceLocation(HMReference.MOD_ID, "textures/gui/material_info.png"), 0, 0, 170, 116);
    }

    @NotNull
    @Override
    public String getUid() {
        return HTJeiPlugin.MATERIAL;
    }

    @NotNull
    @Override
    public String getTitle() {
        return I18n.format(HTJeiPlugin.MATERIAL);
    }

    @NotNull
    @Override
    public String getModName() {
        return HMReference.MOD_NAME;
    }

    @NotNull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout recipeLayout, @NotNull HTMaterialRecipeWrapper recipeWrapper, @NotNull IIngredients ingredients) {
        //FluidStacks
        recipeLayout.getFluidStacks().init(0, false, 4 + 1, 4 + 1);
        recipeLayout.getFluidStacks().set(0, recipeWrapper.getFluidStacks());
        //ItemStack
        for (int i = 0; i < recipeWrapper.getItemStacks().size(); i++) {
            recipeLayout.getItemStacks().init(i, true, 18 * (i % 9) + 4, 18 * (i / 9) + 18 + 4);
            recipeLayout.getItemStacks().set(i, recipeWrapper.getItemStacks().get(i));
        }
    }

}