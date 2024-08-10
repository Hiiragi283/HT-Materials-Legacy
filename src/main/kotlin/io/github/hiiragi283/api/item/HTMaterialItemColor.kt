package io.github.hiiragi283.api.item

import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.material.property.HTMaterialProperties
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.ItemStack

object HTMaterialItemColor : IItemColor {
    override fun colorMultiplier(stack: ItemStack, tintIndex: Int): Int =
        HTMaterialsAPI.instance.partManager[stack]
            ?.material
            ?.get(HTMaterialProperties.COLOR)
            ?.rgb
            ?.takeIf { tintIndex == 0 }
            ?: -1
}