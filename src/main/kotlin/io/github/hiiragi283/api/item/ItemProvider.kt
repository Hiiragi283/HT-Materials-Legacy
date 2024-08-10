package io.github.hiiragi283.api.item

import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

interface ItemProvider {
    fun asItem(): Item

    fun asItemStack(count: Int, meta: Int): ItemStack = ItemStack(asItem(), count, meta)

    companion object {
        @JvmStatic
        fun castOrNull(any: Any): Item? = if (any is ItemProvider) any.asItem() else null

        @JvmStatic
        fun castOrAir(any: Any): Item = if (any is ItemProvider) any.asItem() else Items.AIR
    }
}