package io.github.hiiragi283.bwms.api.shape

import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder

fun interface HTShape {
	fun canGenerateItem(materialKey: HTMaterialKey, material: HTPropertyHolder): Boolean
}
