package io.github.hiiragi283.bwms.api.extension

import com.mojang.nbt.CompoundTag
import com.mojang.nbt.ListTag
import com.mojang.nbt.Tag

//    CompoundTag    //

fun buildCompoundTag(builderAction: CompoundTag.() -> Unit): CompoundTag = CompoundTag().apply(builderAction)

// fun NBTTagCompound.putIdentifier(key: String, value: ResourceLocation) = setString(key, value.toString())

fun <V : Any> Map<String, V>.toCompoundTag(mapping: (V) -> Tag<*>): CompoundTag = buildCompoundTag {
	this@toCompoundTag.mapValues { mapping(it.value) }.forEach { (key: String, tag: Tag<*>) -> put(key, tag) }
}

fun <K : Any, V : Any> Map<K, V>.toCompoundTag(
	keyMapping: (K) -> String,
	valueMapping: (V) -> Tag<*>
): CompoundTag =
	this@toCompoundTag.mapKeys { keyMapping(it.key) }.toCompoundTag(mapping = valueMapping)

//    ListTag    //

fun buildListTag(builderAction: ListTag.() -> Unit): ListTag = ListTag().apply(builderAction)

// fun NBTTagList.add(value: ResourceLocation) = appendTag(NBTTagString(value.toString()))

fun Iterable<Tag<*>>.toListTag(): ListTag = toListTag { it }

fun <T> Iterable<T>.toListTag(mapping: (T) -> Tag<*>): ListTag = buildListTag {
	this@toListTag.map(mapping).forEach(::addTag)
}
