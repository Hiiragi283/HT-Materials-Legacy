package io.github.hiiragi283.bwms.api.property

import io.github.hiiragi283.bwms.api.extension.TypedResourceLocation

interface HTPropertyHolder {
	operator fun <T : Any> get(id: TypedResourceLocation<T>): T?

	fun <T : Any> getOrDefault(id: TypedResourceLocation<T>, defaultValue: T): T = get(id) ?: defaultValue

	operator fun contains(id: TypedResourceLocation<*>): Boolean

	fun forEachProperties(action: (TypedResourceLocation<*>, Any) -> Unit)

	interface Mutable : HTPropertyHolder {
		operator fun <T : Any> set(id: TypedResourceLocation<T>, value: T)

		fun remove(id: TypedResourceLocation<*>)
	}

	companion object {
		@JvmField
		val EMPTY: HTPropertyHolder = Empty

		@JvmStatic
		fun create(
			map: MutableMap<TypedResourceLocation<*>, Any> = mutableMapOf(),
			builderAction: Mutable.() -> Unit = {}
		): HTPropertyHolder =
			Builder(map).apply(builderAction)
	}

	private object Empty : HTPropertyHolder {
		override fun <T : Any> get(id: TypedResourceLocation<T>): T? = null

		override fun contains(id: TypedResourceLocation<*>): Boolean = false

		override fun forEachProperties(action: (TypedResourceLocation<*>, Any) -> Unit) = Unit
	}

	private class Builder(private val map: MutableMap<TypedResourceLocation<*>, Any>) : Mutable {
		override fun <T : Any> get(id: TypedResourceLocation<T>): T? = id.cast(map[id])

		override fun contains(id: TypedResourceLocation<*>): Boolean = id in map

		override fun forEachProperties(action: (TypedResourceLocation<*>, Any) -> Unit) {
			map.forEach(action)
		}

		override fun <T : Any> set(id: TypedResourceLocation<T>, value: T) {
			map[id] = value
		}

		override fun remove(id: TypedResourceLocation<*>) {
			map.remove(id)
		}
	}
}
