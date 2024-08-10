package io.github.hiiragi283.bwms.api

import io.github.hiiragi283.bwms.api.extension.runCatchAndLog
import io.github.hiiragi283.bwms.api.item.HTMaterialItemConvertible
import io.github.hiiragi283.bwms.api.material.HTMaterialRegistry
import io.github.hiiragi283.bwms.api.part.HTPart
import io.github.hiiragi283.bwms.api.part.HTPartManager
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import io.github.hiiragi283.bwms.api.shape.HTShapeRegistry
import io.github.hiiragi283.bwms.common.BWMsAPIImpl

interface BWMsAPI {
	companion object {
		const val MOD_ID: String = "better_with_materials"
		const val MOD_NAME: String = "Better With Materials"
		const val MAX_METADATA: Int = Short.MAX_VALUE.toInt()

		@JvmStatic
		val instance: BWMsAPI = BWMsAPIImpl
	}

	val materialRegistry: HTMaterialRegistry
	val shapeRegistry: HTShapeRegistry
	val partManager: HTPartManager

	val materialItemMap: Map<HTShapeKey, HTMaterialItemConvertible>

	/*fun forEachPhasedMaterial(action: (HTPhasedMaterial) -> Unit) {
		materialRegistry.keys.forEach { materialKey ->
			HTFluidPhase.entries.map { phase ->
				HTPhasedMaterial(materialKey, phase)
			}.forEach { runCatchAndLog { action(it) } }
		}
	}*/

	fun forEachPart(action: (HTPart) -> Unit) {
		materialRegistry.keys.forEach { materialKey ->
			shapeRegistry.keys.map { shapeKey ->
				HTPart(materialKey, shapeKey)
			}.forEach { runCatchAndLog { action(it) } }
		}
	}

	fun forEachAddon(action: (BWMsPlugin) -> Unit)

}
