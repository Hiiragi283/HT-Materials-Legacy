package io.github.hiiragi283.bwms.api.material.property

import io.github.hiiragi283.bwms.api.material.HTMaterialTooltipContext

fun interface HTTooltipProperty {
	fun appendTooltip(context: HTMaterialTooltipContext)
}
