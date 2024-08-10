package io.github.hiiragi283.api.extension

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ResourceLocation
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i
import java.awt.Color

//    Vec3d    //

operator fun Vec3d.component1(): Double = x

operator fun Vec3d.component2(): Double = y

operator fun Vec3d.component3(): Double = z

//    Vec3i    //

operator fun Vec3i.component1(): Int = x

operator fun Vec3i.component2(): Int = y

operator fun Vec3i.component3(): Int = z

//    Color    //

operator fun Color.component1(): Int = red

operator fun Color.component2(): Int = green

operator fun Color.component3(): Int = blue

//    Identifier    //

operator fun ResourceLocation.component1(): String = namespace

operator fun ResourceLocation.component2(): String = path

//    ItemStack    //

operator fun ItemStack.component1(): Item = item

operator fun ItemStack.component2(): Int = count

operator fun ItemStack.component3(): NBTTagCompound? = tagCompound