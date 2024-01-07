package io.github.hiiragi283.material.compat.jei;

import io.github.hiiragi283.material.HMReference;
import io.github.hiiragi283.material.HTMaterialsMod;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

@JEIPlugin
public class HTJeiPlugin implements IModPlugin {

    public static final String MATERIAL = HMReference.MOD_ID + ".material";

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new HTMaterialRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(@NotNull IModRegistry registry) {
        registry.handleRecipes(HTMaterialKey.class, HTMaterialRecipeWrapper::new, MATERIAL);
        registry.addRecipes(HTMaterial.getMaterialKeys().collect(Collectors.toList()), MATERIAL);
        registry.addRecipeCatalyst(new ItemStack(HTMaterialsMod.ICON), MATERIAL);
    }
}