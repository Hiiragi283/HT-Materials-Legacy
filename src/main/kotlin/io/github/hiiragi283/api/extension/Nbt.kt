package io.github.hiiragi283.api.extension

import net.minecraft.nbt.NBTBase
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.nbt.NBTTagString
import net.minecraft.util.ResourceLocation

//    NBTTagCompound    //

fun buildNbt(builderAction: NBTTagCompound.() -> Unit): NBTTagCompound = NBTTagCompound().apply(builderAction)

fun NBTTagCompound.putIdentifier(key: String, value: ResourceLocation) = setString(key, value.toString())

fun <V : Any> Map<String, V>.toNbtCompound(mapping: (V) -> NBTBase): NBTTagCompound = buildNbt {
    this@toNbtCompound.mapValues { mapping(it.value) }.forEach { (key: String, nbt: NBTBase) -> setTag(key, nbt) }
}

fun <K : Any, V : Any> Map<K, V>.toNbtCompound(
    keyMapping: (K) -> String,
    valueMapping: (V) -> NBTBase
): NBTTagCompound =
    this@toNbtCompound.mapKeys { keyMapping(it.key) }.toNbtCompound(mapping = valueMapping)

//    NbtList    //

fun buildNbtList(builderAction: NBTTagList.() -> Unit): NBTTagList = NBTTagList().apply(builderAction)

fun NBTTagList.add(value: ResourceLocation) = appendTag(NBTTagString(value.toString()))

fun Iterable<NBTBase>.toNbtList(): NBTTagList = toNbtList { it }

fun <T> Iterable<T>.toNbtList(mapping: (T) -> NBTBase): NBTTagList = buildNbtList {
    this@toNbtList.map(mapping).forEach(::appendTag)
}