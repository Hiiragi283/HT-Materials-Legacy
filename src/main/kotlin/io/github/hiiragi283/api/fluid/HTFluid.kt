package io.github.hiiragi283.api.fluid

import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.material.property.HTMaterialProperties
import io.github.hiiragi283.api.property.HTPropertyHolder
import net.minecraftforge.fluids.Fluid

class HTFluid(
    val materialKey: HTMaterialKey,
    val material: HTPropertyHolder,
    val fluidPhase: HTFluidPhase
) : Fluid(materialKey.name, fluidPhase.textureId, fluidPhase.textureId) {

    init {
        material.get(HTMaterialProperties.COLOR)?.let(::setColor)
    }

}