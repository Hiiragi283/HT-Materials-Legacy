package io.github.hiiragi283.bwms.api.material

import io.github.hiiragi283.bwms.api.material.property.HTMaterialProperties
import io.github.hiiragi283.bwms.api.material.property.HTTooltipProperty
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import net.minecraft.core.item.ItemStack
import net.minecraft.core.lang.I18n

data class HTMaterialTooltipContext(
	val materialKey: HTMaterialKey,
	val material: HTPropertyHolder,
	val materialTranslatable: HTShapeKey? = null,
	val stack: ItemStack = ItemStack.NO_ITEM,
) {
	fun createTooltips(): MutableList<String> = appendTooltips(mutableListOf())

	fun appendTooltips(tooltip: MutableList<String>): MutableList<String> {
		val i18n: I18n = I18n.getInstance()
		// Title
		tooltip.add(i18n.translateKey("tooltip.ht_materials.material.title"))
		// Name
		val name: String = materialTranslatable?.getTranslatedName(materialKey) ?: materialKey.translatedName
		tooltip.add(i18n.translateKeyAndFormat("tooltip.ht_materials.material.name", name))
		// HTShapeType
		// tooltip.add(i18n.format("tooltip.ht_materials.material.type", material.type.translatedName))
		// Formula
		material[HTMaterialProperties.FORMULA]?.let { formula: String ->
			tooltip.add(i18n.translateKeyAndFormat("tooltip.ht_materials.material.formula", formula))
		}
		// Molar Mass
		material[HTMaterialProperties.MOLAR]?.let { molar: Double ->
			tooltip.add(i18n.translateKeyAndFormat("tooltip.ht_materials.material.molar", molar))
		}
		// Tooltip from Properties
		material.forEachProperties { _, property ->
			if (property is HTTooltipProperty) {
				property.appendTooltip(this)
			}
		}
		return tooltip
	}

	fun appendTooltips(builder: StringBuilder): StringBuilder = builder.apply {
		val i18n: I18n = I18n.getInstance()
		// Title
		append(i18n.translateKey("tooltip.better_with_materials.material.title"))
		// Name
		val name: String = materialTranslatable?.getTranslatedName(materialKey) ?: materialKey.translatedName
		append('\n')
		append(i18n.translateKeyAndFormat("tooltip.better_with_materials.material.name", name))
		// HTShapeType
		// tooltip.add(i18n.format("tooltip.ht_materials.material.type", material.type.translatedName))
		// Formula
		material[HTMaterialProperties.FORMULA]?.let { formula: String ->
			append('\n')
			append(i18n.translateKeyAndFormat("tooltip.better_with_materials.material.formula", formula))
		}
		// Molar Mass
		material[HTMaterialProperties.MOLAR]?.let { molar: Double ->
			append('\n')
			append(i18n.translateKeyAndFormat("tooltip.better_with_materials.material.molar", molar))
		}
		append('\n')
		// Tooltip from Properties
		/*material.forEachProperties { _, property ->
			if (property is HTTooltipProperty) {
				property.appendTooltip(this@HTMaterialTooltipContext)
			}
		}*/
	}
}
