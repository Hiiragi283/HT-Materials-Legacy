package io.github.hiiragi283.api.extension

import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.FurnaceRecipes
import net.minecraft.item.crafting.Ingredient
import net.minecraft.item.crafting.ShapedRecipes
import net.minecraft.item.crafting.ShapelessRecipes
import net.minecraft.util.NonNullList
import net.minecraft.util.ResourceLocation
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.fml.common.registry.ForgeRegistries

inline fun buildShapedPrimer(
    height: Int,
    width: Int,
    builderAction: CraftingHelper.ShapedPrimer.() -> Unit
): CraftingHelper.ShapedPrimer = CraftingHelper.ShapedPrimer()
    .apply(builderAction)
    .apply {
        this.height = height
        this.width = width
    }

inline fun registerShapedRecipe(
    id: ResourceLocation,
    output: ItemStack,
    height: Int,
    width: Int,
    group: ResourceLocation? = null,
    builderAction: CraftingHelper.ShapedPrimer.() -> Unit
) {
    val primer: CraftingHelper.ShapedPrimer = buildShapedPrimer(height, width, builderAction)
    ForgeRegistries.RECIPES.register(
        ShapedRecipes(
            group?.toString() ?: "",
            primer.width,
            primer.height,
            primer.input,
            output
        ).setRegistryName(id)
    )
}

fun registerShapelessRecipe(
    id: ResourceLocation,
    output: ItemStack,
    vararg ingredients: Ingredient,
    group: ResourceLocation? = null,
) {
    ForgeRegistries.RECIPES.register(
        ShapelessRecipes(
            group?.toString() ?: "",
            output,
            NonNullList.from(Ingredient.EMPTY, *ingredients)
        ).setRegistryName(id)
    )
}

fun registerSmeltingRecipe(
    block: Block,
    meta: Int = 0,
    output: ItemStack,
    exp: Float = 0.0f
) {
    registerSmeltingRecipe(Item.getItemFromBlock(block), meta, output, exp)
}

fun registerSmeltingRecipe(
    item: Item,
    meta: Int = 0,
    output: ItemStack,
    exp: Float = 0.0f
) {
    FurnaceRecipes.instance().addSmeltingRecipe(
        ItemStack(item, 1, meta),
        output,
        exp
    )
}