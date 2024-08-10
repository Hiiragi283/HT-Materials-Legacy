package io.github.hiiragi283.api.material

import io.github.hiiragi283.api.material.property.HTMaterialProperties
import io.github.hiiragi283.api.material.property.HTTooltipProperty
import io.github.hiiragi283.api.property.HTPropertyHolder
import io.github.hiiragi283.api.shape.HTShapeKey
import net.minecraft.client.resources.I18n
import net.minecraft.item.ItemStack

data class HTMaterialTooltipContext(
    val materialKey: HTMaterialKey,
    val material: HTPropertyHolder,
    val materialTranslatable: HTShapeKey? = null,
    val stack: ItemStack = ItemStack.EMPTY,
) {
    fun createTooltips(): MutableList<String> = appendTooltips(mutableListOf())

    fun appendTooltips(tooltip: MutableList<String>): MutableList<String> {
        // Title
        tooltip.add(I18n.format("tooltip.ht_materials.material.title"))
        // Name
        val name: String = materialTranslatable?.getTranslatedName(materialKey) ?: materialKey.translatedName
        tooltip.add(I18n.format("tooltip.ht_materials.material.name", name))
        // HTShapeType
        // tooltip.add(I18n.format("tooltip.ht_materials.material.type", material.type.translatedName))
        // Formula
        material[HTMaterialProperties.FORMULA]?.let { formula: String ->
            tooltip.add(I18n.format("tooltip.ht_materials.material.formula", formula))
        }
        // Molar Mass
        material[HTMaterialProperties.MOLAR]?.let { molar: Double ->
            tooltip.add(I18n.format("tooltip.ht_materials.material.molar", molar))
        }
        // Tooltip from Properties
        material.forEachProperties { _, property ->
            if (property is HTTooltipProperty) {
                property.appendTooltip(this)
            }
        }
        return tooltip
    }
}