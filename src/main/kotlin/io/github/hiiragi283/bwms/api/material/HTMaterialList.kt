package io.github.hiiragi283.bwms.api.material

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder

class HTMaterialList private constructor(private val delegate: List<Entry>) : Iterable<HTMaterialList.Entry> {

	companion object {
		@JvmStatic
		fun fromKeys(keys: Collection<HTMaterialKey>): HTMaterialList {
			check(keys.isNotEmpty()) { "keys must not be empty" }
			val materialRegistry: HTMaterialRegistry = BWMsAPI.instance.materialRegistry
			return HTMaterialList(keys.mapNotNull {
				val index: Int = materialRegistry.getIndex(it) ?: return@mapNotNull null
				val material: HTPropertyHolder = materialRegistry[it] ?: return@mapNotNull null
				Entry(it, index, material)
			})

		}

		@JvmStatic
		fun fromIndexes(indexes: Collection<Int>): HTMaterialList {
			check(indexes.isNotEmpty()) { "keys must not be empty" }
			val materialRegistry: HTMaterialRegistry = BWMsAPI.instance.materialRegistry
			return HTMaterialList(indexes.mapNotNull {
				val key: HTMaterialKey = materialRegistry.getKey(it) ?: return@mapNotNull null
				val material: HTPropertyHolder = materialRegistry[it] ?: return@mapNotNull null
				Entry(key, it, material)
			})
		}
	}

	init {
		check(delegate.isNotEmpty()) { "Could not construct HTMaterialList with no materials!" }
	}

	operator fun get(key: HTMaterialKey): Entry? = delegate.firstOrNull { it.key == key }

	operator fun get(index: Int): Entry? = delegate.firstOrNull { it.index == index }

	operator fun contains(key: HTMaterialKey): Boolean = delegate.any { it.key == key }

	operator fun contains(index: Int): Boolean = delegate.any { it.index == index }

	//    Iterable    //

	override fun iterator(): Iterator<Entry> = delegate.iterator()

	//    Entry    //

	data class Entry(
		val key: HTMaterialKey,
		val index: Int,
		val material: HTPropertyHolder
	) : Comparable<Entry> {
		override fun compareTo(other: Entry): Int = other.index - this.index
	}

}
