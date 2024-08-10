package io.github.hiiragi283.api.extension

import io.github.hiiragi283.api.item.ItemProvider
import net.minecraft.block.Block
import net.minecraft.item.Item

//    Block    //

fun Block.asItem(): Item = (this as ItemProvider).asItem()