package io.github.hiiragi283.bwms.api.item

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.extension.HTColor
import io.github.hiiragi283.bwms.api.material.HTMaterialList
import io.github.hiiragi283.bwms.api.material.HTMaterialType
import io.github.hiiragi283.bwms.api.material.property.HTMaterialProperties
import io.github.hiiragi283.bwms.api.part.HTPart
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import useless.prismaticlibe.ColoredTexture
import useless.prismaticlibe.IColored
import java.awt.Color

class HTMaterialItem(
	index: Int,
	override val shapeKey: HTShapeKey,
	override val entryList: HTMaterialList
) : Item(index), HTMaterialItemConvertible, IColored {

	init {
		hasSubtypes = true
		key = shapeKey.name
	}

	override fun getTranslatedName(itemstack: ItemStack): String = getMaterialKey(itemstack)
		?.let { shapeKey.getTranslatedName(it) }
		?: "Unknown Material"

	//    IColored    //

	override fun getTextures(stack: ItemStack): Array<ColoredTexture> {
		val part: HTPart = BWMsAPI.instance.partManager[stack] ?: return emptyArray()
		val material: HTPropertyHolder = part.material
		val shapeKey: HTShapeKey = part.shapeKey
		return material.getOrDefault(
			HTMaterialProperties.itemTexture(shapeKey),
			IColored { _ ->
				val color: Color = material.getOrDefault(HTMaterialProperties.COLOR, HTColor.WHITE)
				material.getOrDefault(HTMaterialProperties.TYPE, HTMaterialType.Solid).getTextures(shapeKey)
					.map { ColoredTexture(BWMsAPI.MOD_ID, it, color) }
					.toTypedArray()
			}
		).getTextures(stack) ?: emptyArray()
	}

}
