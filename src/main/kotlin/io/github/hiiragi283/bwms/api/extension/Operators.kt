package io.github.hiiragi283.bwms.api.extension

import com.google.common.collect.Table
import com.mojang.nbt.CompoundTag
import net.minecraft.core.item.Item
import net.minecraft.core.item.ItemStack
import net.minecraft.core.util.phys.Vec3d
import java.awt.Color

//    Vec3d    //

operator fun Vec3d.component1(): Double = xCoord

operator fun Vec3d.component2(): Double = yCoord

operator fun Vec3d.component3(): Double = zCoord

//    Vec3i    //

// operator fun Vec3i.component1(): Int = x

// operator fun Vec3i.component2(): Int = y

// operator fun Vec3i.component3(): Int = z

//    Color    //

operator fun Color.component1(): Int = red

operator fun Color.component2(): Int = green

operator fun Color.component3(): Int = blue

//    Identifier    //

// operator fun ResourceLocation.component1(): String = namespace

// operator fun ResourceLocation.component2(): String = path

//    ItemStack    //

operator fun ItemStack.component1(): Item = item

operator fun ItemStack.component2(): Int = stackSize

operator fun ItemStack.component3(): Int = metadata

operator fun ItemStack.component4(): CompoundTag = data

//    Cell    //

operator fun <R : Any, C : Any, V : Any> Table.Cell<R, C, V>.component1(): R = rowKey

operator fun <R : Any, C : Any, V : Any> Table.Cell<R, C, V>.component2(): C = columnKey

operator fun <R : Any, C : Any, V : Any> Table.Cell<R, C, V>.component3(): V = value
