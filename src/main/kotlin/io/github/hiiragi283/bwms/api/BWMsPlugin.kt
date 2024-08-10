package io.github.hiiragi283.bwms.api

import io.github.hiiragi283.bwms.api.material.HTMaterialRegistry
import io.github.hiiragi283.bwms.api.part.HTPartManager
import io.github.hiiragi283.bwms.api.shape.HTShapeRegistry

interface BWMsPlugin {

	val modId: String
	val priority: Int

	fun registerShape(builder: HTShapeRegistry.Builder) {}

	fun registerMaterial(builder: HTMaterialRegistry.Builder) {}

	fun afterMaterialRegistration(instance: BWMsAPI, isClient: Boolean) {}

	fun bindItemWithPart(builder: HTPartManager.Builder) {}

	/*class RegisterEvent(private val list: MutableList<BWMsPlugin>) : Event() {

		fun register(plugin: BWMsPlugin) {
			list.add(plugin)
		}
	}*/

}
