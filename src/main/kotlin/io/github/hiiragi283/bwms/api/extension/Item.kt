package io.github.hiiragi283.bwms.api.extension

import net.minecraft.core.item.ItemStack

//    Item    //

//    ItemStack    //

val ItemStack.isEmpty: Boolean
	get() = this == ItemStack.NO_ITEM || stackSize <= 0

val ItemStack.isNotEmpty: Boolean
	get() = !isEmpty

val ItemStack.isWild: Boolean
	get() = metadata !in (Short.MIN_VALUE..Short.MAX_VALUE)

val ItemStack.isNotWild: Boolean
	get() = !isWild
