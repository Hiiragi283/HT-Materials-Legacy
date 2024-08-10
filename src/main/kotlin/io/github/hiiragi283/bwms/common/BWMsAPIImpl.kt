package io.github.hiiragi283.bwms.common

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.BWMsPlugin
import io.github.hiiragi283.bwms.api.extension.getEntrypoints
import io.github.hiiragi283.bwms.api.extension.isModLoaded
import io.github.hiiragi283.bwms.api.item.HTMaterialItemConvertible
import io.github.hiiragi283.bwms.api.material.HTMaterialRegistry
import io.github.hiiragi283.bwms.api.part.HTPartManager
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import io.github.hiiragi283.bwms.api.shape.HTShapeRegistry

internal object BWMsAPIImpl : BWMsAPI {
	override var materialRegistry: HTMaterialRegistry = HTMaterialRegistry.EMPTY
	override var shapeRegistry: HTShapeRegistry = HTShapeRegistry.EMPTY
	override var partManager: HTPartManager = HTPartManager.EMPTY
	override var materialItemMap: Map<HTShapeKey, HTMaterialItemConvertible> = emptyMap()

	private val plugins: Iterable<BWMsPlugin> = getEntrypoints("better_with_materials", BWMsPlugin::class.java)
		.filter { isModLoaded(it.modId) }
		.sortedWith(compareBy<BWMsPlugin> { it.priority }.thenBy { it::class.java.canonicalName })

	override fun forEachAddon(action: (BWMsPlugin) -> Unit) {
		plugins.forEach(action)
	}
}
