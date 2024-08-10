package io.github.hiiragi283.api.material.property

import io.github.hiiragi283.api.shape.HTShapeKey
import net.minecraft.dispenser.IBlockSource
import net.minecraft.item.ItemStack

fun interface HTMaterialDispenseBehavior {
    fun dispense(source: IBlockSource, stack: ItemStack, shapeKey: HTShapeKey): ItemStack
}