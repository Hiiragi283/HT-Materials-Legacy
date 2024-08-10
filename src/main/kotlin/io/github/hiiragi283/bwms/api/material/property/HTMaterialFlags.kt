package io.github.hiiragi283.bwms.api.material.property

import io.github.hiiragi283.bwms.api.extension.TypedResourceLocation

object HTMaterialFlags {
	private val registry: MutableMap<String, TypedResourceLocation<Unit>> = hashMapOf()

	@JvmStatic
	fun getOrCreateFlag(modid: String, path: String): TypedResourceLocation<Unit> =
		registry.computeIfAbsent("$modid:$path", TypedResourceLocation.Companion::of)
}
