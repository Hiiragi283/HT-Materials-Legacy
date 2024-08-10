package io.github.hiiragi283.api.shape

import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.property.HTPropertyHolder

fun interface HTShape {
    fun canGenerateItem(materialKey: HTMaterialKey, material: HTPropertyHolder): Boolean
}