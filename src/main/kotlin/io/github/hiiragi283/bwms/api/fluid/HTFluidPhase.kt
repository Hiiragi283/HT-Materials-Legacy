package io.github.hiiragi283.bwms.api.fluid

import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.material.HTMaterialTranslatable
import io.github.hiiragi283.bwms.api.material.property.HTMaterialProperties
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder

enum class HTFluidPhase(
	val textureId: String,
) : HTMaterialTranslatable {
	POWDER(("block/white_concrete_powder")),
	LIQUID(("block/mushroom_stem")),
	GAS(("block/white_concrete")),
	PLASMA(("block/white_concrete")),
	;

	private val blacklist: MutableList<HTMaterialKey> = mutableListOf()

	fun addBlacklist(vararg keys: HTMaterialKey) = apply {
		keys.forEach(blacklist::add)
	}

	fun canGenerateFluid(materialKey: HTMaterialKey, material: HTPropertyHolder): Boolean =
		materialKey !in blacklist && this in material.getOrDefault(HTMaterialProperties.FLUID_SET, emptySet())

	//    HTMaterialTranslatable    //

	override val translationKey: String = "ht_shape.$name"
}
