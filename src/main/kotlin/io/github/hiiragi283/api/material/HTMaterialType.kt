package io.github.hiiragi283.api.material

import com.google.gson.JsonObject
import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.extension.buildJson
import io.github.hiiragi283.api.extension.suffix
import io.github.hiiragi283.api.fluid.HTFluidPhase
import io.github.hiiragi283.api.shape.HTShapeKey
import io.github.hiiragi283.api.shape.HTShapeKeys
import net.minecraft.util.ResourceLocation

sealed interface HTMaterialType {
    val blockSet: Set<HTShapeKey>
    val fluidSet: Set<HTFluidPhase>
    val itemSet: Set<HTShapeKey>

    fun layer0(shapeKey: HTShapeKey): ResourceLocation = HTMaterialsAPI.id("items/$shapeKey")

    fun layer1(shapeKey: HTShapeKey): ResourceLocation? = layer0(shapeKey).suffix("_overlay")

    fun createModel(shapeKey: HTShapeKey): JsonObject = buildJson {
        addProperty("parent", "item/generated")
        add("textures", buildJson {
            addProperty("layer0", layer0(shapeKey).toString())
            layer1(shapeKey)?.let {
                addProperty("layer1", it.toString())
            }
        })
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
            HTShapeKeys.BLOCK,
            HTShapeKeys.ORE,
            HTShapeKeys.ORE_BLACKSTONE,
            HTShapeKeys.ORE_END,
            HTShapeKeys.ORE_GRAVEL,
            HTShapeKeys.ORE_NETHER,
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

        override fun layer0(shapeKey: HTShapeKey): ResourceLocation = when (shapeKey) {
            HTShapeKeys.GEM -> HTMaterialsAPI.id("items/gem_${name.lowercase()}")
            else -> super.layer0(shapeKey)
        }

        override fun layer1(shapeKey: HTShapeKey): ResourceLocation? = super.layer1(shapeKey)?.takeIf { hasOverlay }
    }

    enum class Metal(val isShiny: Boolean) : HTMaterialType {
        SHINY(true),
        DULL(false),
        ;

        override val blockSet: Set<HTShapeKey> = setOf(
            HTShapeKeys.BLOCK,
            HTShapeKeys.ORE,
            HTShapeKeys.ORE_BLACKSTONE,
            HTShapeKeys.ORE_END,
            HTShapeKeys.ORE_GRAVEL,
            HTShapeKeys.ORE_NETHER,
            HTShapeKeys.ORE_SAND,
        )
        override val fluidSet: Set<HTFluidPhase> = setOf(HTFluidPhase.LIQUID)
        override val itemSet: Set<HTShapeKey> = setOf(
            HTShapeKeys.DUST,
            HTShapeKeys.GEAR,
            HTShapeKeys.INGOT,
            HTShapeKeys.NUGGET,
            HTShapeKeys.PLATE,
            // HTShapeKeys.RAW_CHUNK,
            HTShapeKeys.ROD,
        )

        override fun layer1(shapeKey: HTShapeKey): ResourceLocation? = super.layer1(shapeKey)?.takeIf { isShiny }

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

        override fun layer1(shapeKey: HTShapeKey): ResourceLocation? = null
    }
}