package io.github.hiiragi283.bwms.api.item

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.material.HTMaterialKey
import io.github.hiiragi283.bwms.api.material.HTMaterialList
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import io.github.hiiragi283.bwms.api.shape.HTShape
import io.github.hiiragi283.bwms.api.shape.HTShapeKey
import net.minecraft.core.item.IItemConvertible
import net.minecraft.core.item.ItemStack

interface HTMaterialItemConvertible : IItemConvertible {

	val shapeKey: HTShapeKey

	val shape: HTShape
		get() = shapeKey.get()

	val entryList: HTMaterialList

	fun getStack(index: Int, count: Int = 1): ItemStack? =
		ItemStack(this, count, index).takeIf { index in entryList }

	fun getStack(key: HTMaterialKey, count: Int = 1): ItemStack? =
		BWMsAPI.instance.materialRegistry.getIndex(key)?.let { getStack(it, count) }

	fun getMaterial(stack: ItemStack): HTPropertyHolder? = BWMsAPI.instance.materialRegistry[stack.metadata]

	fun getMaterialKey(stack: ItemStack): HTMaterialKey? =
		BWMsAPI.instance.materialRegistry.getKey(stack.metadata)

	data class Entry(
		val key: HTMaterialKey,
		val index: Int,
		val material: HTPropertyHolder
	) : Comparable<Entry> {
		override fun compareTo(other: Entry): Int = other.index - this.index
	}

}
