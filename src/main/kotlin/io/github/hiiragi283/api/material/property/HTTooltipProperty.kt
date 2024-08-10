package io.github.hiiragi283.api.material.property

import io.github.hiiragi283.api.material.HTMaterialTooltipContext

fun interface HTTooltipProperty {
    fun appendTooltip(context: HTMaterialTooltipContext)
}
