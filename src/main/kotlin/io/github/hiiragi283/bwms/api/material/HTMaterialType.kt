package io.github.hiiragi283.bwms.api.material

import io.github.hiiragi283.bwms.api.fluid.HTFluidPhase
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import io.github.hiiragi283.bwms.api.shape.HTShapeKeys

sealed interface HTMaterialType {
	val blockSet: Set<HTShapeKey>
	val fluidSet: Set<HTFluidPhase>
	val itemSet: Set<HTShapeKey>

	fun layer0(shapeKey: HTShapeKey): String = "${shapeKey.name}.png"

	fun layer1(shapeKey: HTShapeKey): String? = "${shapeKey.name}_overlay.png"

	fun getTextures(shapeKey: HTShapeKey): List<String> {
		val layer0: String = layer0(shapeKey)
		return when (val layer1: String? = layer1(shapeKey)) {
			null -> listOf(layer0)
			else -> listOf(layer0, layer1)
		}
	}

	data object Crop : HTMaterialType {
		override val blockSet: Set<HTShapeKey> = emptySet()
		override val fluidSet: Set<HTFluidPhase> = emptySet()
		override val itemSet: Set<HTShapeKey> = setOf()
	}

	enum class Fluid(override val fluidSet: Set<HTFluidPhase>) : HTMaterialType {
		POWDER(HTFluidPhase.POWDER),
		LIQUID(HTFluidPhase.LIQUID),
		GAS(HTFluidPhase.GAS),
		PLASMA(HTFluidPhase.PLASMA),
		;

		constructor(phase: HTFluidPhase) : this(setOf(phase))

		override val blockSet: Set<HTShapeKey> = emptySet()
		override val itemSet: Set<HTShapeKey> = emptySet()
	}

	enum class Gem(val hasOverlay: Boolean) : HTMaterialType {
		AMETHYST(true),
		COAL(false),
		DIAMOND(true),
		EMERALD(true),
		FLINT(false),
		LAPIS(false),
		QUARTZ(true),
		RUBY(true),
		;

		override val blockSet: Set<HTShapeKey> = setOf(
			HTShapeKeys.STORAGE_BLOCK,
			HTShapeKeys.ORE,
			HTShapeKeys.ORE_BASALT,
			HTShapeKeys.ORE_LIMESTONE,
			HTShapeKeys.ORE_GRANITE,
			HTShapeKeys.ORE_GRAVEL,
			HTShapeKeys.ORE_SAND,
		)
		override val fluidSet: Set<HTFluidPhase> = emptySet()
		override val itemSet: Set<HTShapeKey> = setOf(
			HTShapeKeys.DUST,
			HTShapeKeys.GEAR,
			HTShapeKeys.GEM,
			HTShapeKeys.PLATE,
			// HTShapeKeys.RAW_CHUNK,
			HTShapeKeys.ROD,
		)

		override fun layer0(shapeKey: HTShapeKey): String = when (shapeKey) {
			HTShapeKeys.GEM -> "gem_${name.lowercase()}.png"
			else -> super.layer0(shapeKey)
		}

		override fun layer1(shapeKey: HTShapeKey): String? = super.layer1(shapeKey)?.takeIf { hasOverlay }
	}

	data object Meat : HTMaterialType {
		override val blockSet: Set<HTShapeKey> = emptySet()
		override val fluidSet: Set<HTFluidPhase> = emptySet()
		override val itemSet: Set<HTShapeKey> = setOf()
	}

	enum class Metal(val isShiny: Boolean) : HTMaterialType {
		SHINY(true),
		DULL(false),
		;

		override val blockSet: Set<HTShapeKey> = setOf(
			HTShapeKeys.STORAGE_BLOCK,
			HTShapeKeys.ORE,
			HTShapeKeys.ORE_BASALT,
			HTShapeKeys.ORE_LIMESTONE,
			HTShapeKeys.ORE_GRANITE,
			HTShapeKeys.ORE_GRAVEL,
			HTShapeKeys.ORE_SAND,
		)
		override val fluidSet: Set<HTFluidPhase> = setOf(HTFluidPhase.LIQUID)
		override val itemSet: Set<HTShapeKey> = setOf(
			HTShapeKeys.DUST,
			HTShapeKeys.GEAR,
			HTShapeKeys.INGOT,
			// HTShapeKeys.NUGGET,
			HTShapeKeys.PLATE,
			HTShapeKeys.RAW_CHUNK,
			HTShapeKeys.ROD,
		)

		override fun layer1(shapeKey: HTShapeKey): String? = super.layer1(shapeKey)?.takeIf { isShiny }

		companion object {
			@JvmStatic
			fun fromBoolean(isShiny: Boolean): Metal = when (isShiny) {
				true -> SHINY
				false -> DULL
			}
		}
	}

	data object Solid : HTMaterialType {
		override val blockSet: Set<HTShapeKey> = emptySet()
		override val fluidSet: Set<HTFluidPhase> = emptySet()
		override val itemSet: Set<HTShapeKey> = setOf(HTShapeKeys.DUST)
	}

	data object Wood : HTMaterialType {
		override val blockSet: Set<HTShapeKey> = emptySet()
		override val fluidSet: Set<HTFluidPhase> = emptySet()
		override val itemSet: Set<HTShapeKey> = setOf(
			HTShapeKeys.DUST,
			HTShapeKeys.GEAR,
			HTShapeKeys.PLATE,
		)

		override fun layer1(shapeKey: HTShapeKey): String? = null
	}
}
