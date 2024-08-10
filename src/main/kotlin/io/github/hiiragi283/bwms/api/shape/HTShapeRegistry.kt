package io.github.hiiragi283.bwms.api.shape

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.collect.ImmutableBiMap

class HTShapeRegistry private constructor(private val biMap: BiMap<HTShapeKey, HTShape>) {
	companion object {
		@JvmField
		val EMPTY = HTShapeRegistry(ImmutableBiMap.of())

		@JvmStatic
		fun create(builderAction: Builder.() -> Unit): HTShapeRegistry {
			val map: MutableMap<HTShapeKey, HTShapeBuilder> = mutableMapOf()
			Builder(map).apply(builderAction)
			return map
				.mapValues { it.value.build() }
				.let { HashBiMap.create(it) }
				.let(::HTShapeRegistry)
		}
	}

	//    Map    //

	val keys: Set<HTShapeKey>
		get() = biMap.keys

	val shapes: Collection<HTShape>
		get() = biMap.values

	operator fun contains(key: HTShapeKey): Boolean = key in biMap

	operator fun get(key: HTShapeKey): HTShape? = biMap[key]

	fun getOrDefault(key: HTShapeKey, defaultValue: HTShape): HTShape = get(key) ?: defaultValue

	fun getKey(shape: HTShape): HTShapeKey? = biMap.inverse()[shape]

	fun forEach(action: (HTShapeKey, HTShape) -> Unit) {
		biMap.forEach(action)
	}

	//    Builder    //

	class Builder(private val map: MutableMap<HTShapeKey, HTShapeBuilder>) {
		fun createBlockShape(key: HTShapeKey): HTShapeBuilder {
			check(key !in map) { "Shape builder; $key is already created!" }
			return map
				.computeIfAbsent(key) { HTShapeBuilder.createBlock(key) }
				.apply { key.validated = true }
		}

		fun createItemShape(key: HTShapeKey): HTShapeBuilder {
			check(key !in map) { "Shape builder; $key is already created!" }
			return map
				.computeIfAbsent(key) { HTShapeBuilder.createItem(key) }
				.apply { key.validated = true }
		}

		fun getBuilder(key: HTShapeKey): HTShapeBuilder? = map[key]

		fun canGenerateItem(key: HTShapeKey): Boolean = getBuilder(key)?.allowItemGen == true
	}
}
