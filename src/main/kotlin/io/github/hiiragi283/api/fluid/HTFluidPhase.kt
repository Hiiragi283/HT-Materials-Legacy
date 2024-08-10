package io.github.hiiragi283.api.fluid

import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.material.HTMaterialTranslatable
import io.github.hiiragi283.api.material.property.HTMaterialProperties
import io.github.hiiragi283.api.property.HTPropertyHolder
import net.minecraft.util.ResourceLocation

enum class HTFluidPhase(
    val textureId: ResourceLocation,
) : HTMaterialTranslatable {
    POWDER(ResourceLocation("block/white_concrete_powder")),
    LIQUID(ResourceLocation("block/mushroom_stem")),
    GAS(ResourceLocation("block/white_concrete")),
    PLASMA(ResourceLocation("block/white_concrete")),
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