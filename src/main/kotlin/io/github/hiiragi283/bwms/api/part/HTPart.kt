package io.github.hiiragi283.bwms.api.part

import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import io.github.hiiragi283.bwms.api.shape.HTShape
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.minecraft.client.lang.text.Text

data class HTPart(val materialKey: HTMaterialKey, val shapeKey: HTShapeKey) {

	val material: HTPropertyHolder
		get() = materialKey.get()

	val shape: HTShape
		get() = shapeKey.get()

	val locationName: String = "$materialKey:$shapeKey"

	// val oreDictName: String = shapeKey.getOreDict(materialKey)

	val translation: Text
		@Environment(EnvType.CLIENT)
		get() = shapeKey.getTranslatedText(materialKey)

}
