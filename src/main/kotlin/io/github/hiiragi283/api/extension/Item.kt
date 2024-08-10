package io.github.hiiragi283.api.extension

import io.github.hiiragi283.api.item.ItemProvider
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.NonNullList
import net.minecraftforge.oredict.OreDictionary

//    Item    //

fun Item.asItem(): Item = (this as ItemProvider).asItem()

fun Item.createSubItems(tab: CreativeTabs = CreativeTabs.SEARCH): NonNullList<ItemStack> =
    NonNullList.create<ItemStack?>().apply {
        getSubItems(tab, this)
    }

//    ItemStack    //

val ItemStack.isWild: Boolean
    get() = metadata == OreDictionary.WILDCARD_VALUE

val ItemStack.isNotWild: Boolean
    get() = !isWild

fun ItemStack.parseString(includeNbt: Boolean = false): String = buildString {
    append("${count}x:${item.registryName}:${metadata}")
    if (includeNbt) {
        append(":")
        append("$tagCompound")
    }
}