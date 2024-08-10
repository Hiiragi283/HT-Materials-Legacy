package io.github.hiiragi283.api.part

import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.property.HTPropertyHolder
import io.github.hiiragi283.api.shape.HTShape
import io.github.hiiragi283.api.shape.HTShapeKey
import net.minecraft.util.text.TextComponentTranslation

data class HTPart(val materialKey: HTMaterialKey, val shapeKey: HTShapeKey) {

    val material: HTPropertyHolder
        get() = materialKey.get()

    val shape: HTShape
        get() = shapeKey.get()

    val oreDictName: String = shapeKey.getOreDict(materialKey)

    val translation: TextComponentTranslation
        get() = shapeKey.getTranslatedText(materialKey)

}