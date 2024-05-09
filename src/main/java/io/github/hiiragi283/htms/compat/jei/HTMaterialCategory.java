package io.github.hiiragi283.htms.compat.jei;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.extension.HTColor;
import io.github.hiiragi283.htms.api.material.HTMaterial;
import io.github.hiiragi283.htms.api.shape.HTShape;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiIngredientGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;

public final class HTMaterialCategory implements IRecipeCategory<HTMaterialCategory.Wrapper> {

    @NotNull
    private final IDrawable background;
    @NotNull
    private final IDrawable icon;

    public HTMaterialCategory(IGuiHelper guiHelper) {
        background = guiHelper.createDrawable(HTMaterialsAPI.getId("textures/gui/material_info.png"), 0, 0, 170, 116);
        icon = guiHelper.createDrawableIngredient(new ItemStack(HTMaterialsAPI.INSTANCE.getIconItem()));
    }

    @Override
    public @NotNull String getUid() {
        return HMJEIPlugin.MATERIAL;
    }

    @Override
    public @NotNull String getTitle() {
        return I18n.format("gui." + HMJEIPlugin.MATERIAL);
    }

    @Override
    public @NotNull String getModName() {
        return HMConstants.MOD_NAME;
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayout recipeLayout, @NotNull Wrapper recipeWrapper,
                          @NotNull IIngredients ingredients) {
        // HTMaterial
        IGuiIngredientGroup<HTMaterial> materialGroup = recipeLayout.getIngredientsGroup(HTMaterialHelper.TYPE);
        materialGroup.init(0, true, 5, 5);
        materialGroup.set(0, recipeWrapper.material);
        // FluidStack
        recipeLayout.getFluidStacks().init(0, true, 5, 18 + 5);
        recipeLayout.getFluidStacks().set(0, recipeWrapper.fluidStacks);
        // ItemStack
        List<ItemStack> itemStacks = recipeWrapper.itemStacks;
        for (int i = 0; i < itemStacks.size(); i++) {
            int j = i + 1;
            recipeLayout.getItemStacks().init(i, true, 18 * (j % 9) + 4, 18 * (j / 9) + 18 + 4);
            recipeLayout.getItemStacks().set(i, itemStacks.get(i));
        }
    }

    // Wrapper //

    public static final class Wrapper implements IRecipeWrapper {

        @NotNull
        final HTMaterial material;
        @NotNull
        final List<ItemStack> itemStacks = new ArrayList<>();
        @NotNull
        final List<FluidStack> fluidStacks = new ArrayList<>();

        public Wrapper(@NotNull HTMaterial material) {
            this.material = material;
            for (HTShape shape : HTMaterialsAPI.INSTANCE.getShapeRegistry().values()) {
                String oreDict = shape.getOreDict(material.key());
                itemStacks.addAll(OreDictionary.getOres(oreDict));
            }
            FluidStack fluidStack = FluidRegistry.getFluidStack(material.key().name(), 1000);
            if (fluidStack != null) {
                fluidStacks.add(fluidStack);
            }
        }

        @Override
        public void getIngredients(@NotNull IIngredients ingredients) {
            ingredients.setInput(HTMaterialHelper.TYPE, material);
            ingredients.setInputs(VanillaTypes.ITEM, itemStacks);
            ingredients.setInputs(VanillaTypes.FLUID, fluidStacks);
            ingredients.setOutput(HTMaterialHelper.TYPE, material);
            ingredients.setOutputs(VanillaTypes.ITEM, itemStacks);
            ingredients.setOutputs(VanillaTypes.FLUID, fluidStacks);
        }

        @Override
        public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
            minecraft.fontRenderer.drawString(material.key().getTranslatedName(), 31.0f, 9.5f, HTColor.WHITE.getRGB(),
                    false);
        }
    }
}