package io.github.hiiragi283.api.item

import io.github.hiiragi283.api.HTMaterialsAPI
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

object HTMaterialCreativeTabs : CreativeTabs(HTMaterialsAPI.MOD_ID) {
    override fun createIcon(): ItemStack = ItemStack(Items.IRON_INGOT)
}