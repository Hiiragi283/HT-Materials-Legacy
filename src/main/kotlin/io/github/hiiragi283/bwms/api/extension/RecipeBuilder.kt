package io.github.hiiragi283.bwms.api.extension

import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.part.HTPart
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import net.minecraft.core.data.registry.recipe.RecipeSymbol
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped

fun RecipeBuilderShaped.addInput(symbol: Char, materialKey: HTMaterialKey, shapeKey: HTShapeKey): RecipeBuilderShaped =
	addInput(
		symbol,
		HTPart(materialKey, shapeKey).locationName
	)

fun RecipeBuilderShaped.addInput(symbol: Char, inputs: Collection<HTItemWithMeta>): RecipeBuilderShaped =
	addInput(
		symbol,
		RecipeSymbol(inputs.map(HTItemWithMeta::getStack))
	)
