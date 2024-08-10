package io.github.hiiragi283.bwms.common

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.HTLogger
import io.github.hiiragi283.bwms.api.extension.addInput
import io.github.hiiragi283.bwms.api.extension.buildToml
import io.github.hiiragi283.bwms.api.extension.isClient
import io.github.hiiragi283.bwms.api.item.HTMaterialItem
import io.github.hiiragi283.bwms.api.item.HTMaterialItemConvertible
import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.material.HTMaterialList
import io.github.hiiragi283.bwms.api.material.HTMaterialRegistry
import io.github.hiiragi283.bwms.api.material.property.HTMaterialProperties
import io.github.hiiragi283.bwms.api.part.HTPartManager
import io.github.hiiragi283.bwms.api.shape.HTShape
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import io.github.hiiragi283.bwms.api.shape.HTShapeKeys
import io.github.hiiragi283.bwms.api.shape.HTShapeRegistry
import net.fabricmc.api.ModInitializer
import net.minecraft.core.item.ItemStack
import turniplabs.halplibe.helper.ItemHelper
import turniplabs.halplibe.helper.RecipeBuilder
import turniplabs.halplibe.util.GameStartEntrypoint
import turniplabs.halplibe.util.RecipeEntrypoint
import turniplabs.halplibe.util.TomlConfigHandler

object BetterWithMaterials : ModInitializer, GameStartEntrypoint, RecipeEntrypoint {

	@JvmField
	val CONFIG: TomlConfigHandler = buildToml(buildString {
		append("Better With Materials Config")
		append("\n")
	}) {
		addCategory("ItemID")
			.addEntry("StartID", 20000)
	}.let { TomlConfigHandler(BWMsAPI.MOD_ID, it) }

	//    ModInitializer    //

	override fun onInitialize() {
		HTLogger.log { it.info("=== List ===") }
		BWMsAPI.instance.forEachAddon { plugin ->
			HTLogger.log { logger -> logger.info("${plugin::class.qualifiedName} - Priority: ${plugin.priority}") }
		}
		HTLogger.log { it.info("============") }

		BWMsAPIImpl.shapeRegistry = HTShapeRegistry.create {
			BWMsAPI.instance.forEachAddon { addon ->
				addon.registerShape(this)
			}
		}
		HTLogger.log { it.info("HTShapeRegistry built!") }

		BWMsAPIImpl.materialRegistry = HTMaterialRegistry.create {
			BWMsAPI.instance.forEachAddon { addon ->
				addon.registerMaterial(this)
			}
		}
		HTLogger.log { it.info("HTMaterialRegistry built!") }

		BWMsAPIImpl.materialItemMap = buildMap {
			var itemId: Int = CONFIG.getInt("ItemID.StartID")
			BWMsAPI.instance.shapeRegistry.forEach shape@{ shapeKey: HTShapeKey, shape: HTShape ->
				val keyList: List<HTMaterialKey> = buildList {
					BWMsAPI.instance.materialRegistry.forEach { key, material ->
						if (shape.canGenerateItem(key, material)) {
							add(key)
						}
					}
				}
				if (keyList.isEmpty()) return@shape
				put(
					shapeKey, ItemHelper.createItem(
						BWMsAPI.MOD_ID,
						HTMaterialItem(itemId, shapeKey, HTMaterialList.fromKeys(keyList))
					) as HTMaterialItemConvertible
				)
				HTLogger.log { it.info("Material Item with $shapeKey registered as $itemId!") }
				itemId++
			}
		}

		BWMsAPI.instance.materialItemMap.values.forEach { item ->
			item.entryList.forEach { entry ->
				// CreativeHelper.setPriority(item, entry.index, 1000)
			}
		}
		HTLogger.log { it.info("Material item map built!") }

		HTLogger.log { it.info("BTMs initialized!") }
	}

	//    GameStartEntrypoint    //

	override fun beforeGameStart() {

	}

	override fun afterGameStart() {
		BWMsAPI.instance.forEachAddon { addon ->
			addon.afterMaterialRegistration(BWMsAPI.instance, isClient)
		}
	}

	//    RecipeEntrypoint    //

	override fun onRecipesReady() {
		BWMsAPIImpl.partManager = HTPartManager.create {
			BWMsAPI.instance.forEachAddon { addon ->
				addon.bindItemWithPart(this)
			}
		}
		HTLogger.log { it.info("HTPartManager built!") }

		val itemMap: Map<HTShapeKey, HTMaterialItemConvertible> = BWMsAPI.instance.materialItemMap
		val partManager: HTPartManager = BWMsAPI.instance.partManager
		BWMsAPI.instance.materialRegistry.forEach { key: HTMaterialKey, _ ->
			registerGearRecipe(key, itemMap, partManager)
		}

		HTLogger.log { it.info("Material item recipes registered!") }
	}

	private fun registerBlockConstructionRecipe(
		materialKey: HTMaterialKey,
		itemMap: Map<HTShapeKey, HTMaterialItemConvertible>,
		partManager: HTPartManager
	) {

	}

	private fun registerBlockDecompositionRecipe(
		materialKey: HTMaterialKey,
		itemMap: Map<HTShapeKey, HTMaterialItemConvertible>,
		partManager: HTPartManager
	) {

	}

	private fun registerGearRecipe(
		materialKey: HTMaterialKey,
		itemMap: Map<HTShapeKey, HTMaterialItemConvertible>,
		partManager: HTPartManager
	) {
		val defaultShape: HTShapeKey = materialKey.get()[HTMaterialProperties.DEFAULT_ITEM_SHAPE] ?: return
		val index: Int = BWMsAPI.instance.materialRegistry.getIndex(materialKey) ?: return
		val output: ItemStack = itemMap[HTShapeKeys.GEAR]?.getStack(index) ?: return
		if (!partManager.contains(materialKey, defaultShape)) return
		RecipeBuilder.Shaped(BWMsAPI.MOD_ID)
			.setShape(" A ", "A A", " A ")
			.addInput(
				'A',
				materialKey,
				defaultShape
			)
			.create("${materialKey}_gear", output)
	}

	override fun initNamespaces() {
		RecipeBuilder.initNameSpace(BWMsAPI.MOD_ID)
	}
}
