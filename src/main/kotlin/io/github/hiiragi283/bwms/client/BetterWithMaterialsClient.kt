package io.github.hiiragi283.bwms.client

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.HTLogger
import io.github.hiiragi283.bwms.api.item.HTMaterialItemConvertible
import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.material.property.HTMaterialProperties
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import io.github.hiiragi283.bwms.api.shape.HTShape
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import net.fabricmc.api.ClientModInitializer
import net.minecraft.core.item.ItemStack
import turniplabs.halplibe.helper.TextureHelper

object BetterWithMaterialsClient : ClientModInitializer {
	override fun onInitializeClient() {
		BWMsAPI.instance.forEachPart { part ->
			val materialKey: HTMaterialKey = part.materialKey
			val material: HTPropertyHolder = part.material
			val shapeKey: HTShapeKey = part.shapeKey
			val shape: HTShape = part.shape
			if (shape.canGenerateItem(materialKey, material)) {
				material[HTMaterialProperties.TYPE]?.let { type ->
					registerTexture(type.layer0(shapeKey))
					type.layer1(shapeKey)?.let(::registerTexture)
				}


			}
		}
		BWMsAPI.instance.materialItemMap.forEach { (shapeKey: HTShapeKey, item: HTMaterialItemConvertible) ->
			item.entryList.forEach { (_, index: Int, material: HTPropertyHolder) ->
				material[HTMaterialProperties.itemTexture(shapeKey)]
					?.getTextures(ItemStack(item, 1, index))
			}
		}
		HTLogger.log { it.info("BTMs client-initialized!") }
	}

	private fun registerTexture(name: String) {
		TextureHelper.getOrCreateItemTexture(BWMsAPI.MOD_ID, name)
	}
}
