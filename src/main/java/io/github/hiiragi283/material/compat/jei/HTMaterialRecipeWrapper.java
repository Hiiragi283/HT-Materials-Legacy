package io.github.hiiragi283.material.compat.jei;

import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.util.HTColor;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HTMaterialRecipeWrapper implements IRecipeWrapper {

    private final HTMaterialKey materialKey;
    private final List<FluidStack> fluidStacks;
    private final List<ItemStack> itemStacks;

    public HTMaterialRecipeWrapper(HTMaterialKey materialKey) {
        this.materialKey = materialKey;
        this.fluidStacks = Collections.singletonList(FluidRegistry.getFluidStack(materialKey.name(), 1000));
        this.itemStacks = HTMaterialUtils.getMatchingStacks(materialKey).collect(Collectors.toList());
    }

    public List<FluidStack> getFluidStacks() {
        return new ArrayList<>(fluidStacks);
    }

    public List<ItemStack> getItemStacks() {
        return new ArrayList<>(itemStacks);
    }

    @Override
    public void getIngredients(@NotNull IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, getItemStacks());
        ingredients.setInputs(VanillaTypes.FLUID, getFluidStacks());
        ingredients.setOutputs(VanillaTypes.ITEM, getItemStacks());
        ingredients.setOutputs(VanillaTypes.FLUID, getFluidStacks());
    }

    @Override
    public void drawInfo(@NotNull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        minecraft.fontRenderer.drawString(materialKey.getTranslatedName(), 24, 10, HTColor.WHITE.getRGB());
    }

}