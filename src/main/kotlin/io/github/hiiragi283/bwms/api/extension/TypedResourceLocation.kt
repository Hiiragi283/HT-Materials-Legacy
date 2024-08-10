package io.github.hiiragi283.bwms.api.extension

data class TypedResourceLocation<T : Any>(val clazz: Class<T>, val modid: String, val path: String) {
	companion object {
		@JvmStatic
		inline fun <reified T : Any> of(name: String): TypedResourceLocation<T> = name
			.split(":", limit = 2)
			.let { TypedResourceLocation(T::class.java, it[0], it[1]) }

		@JvmStatic
		inline fun <reified T : Any> of(namespace: String, path: String): TypedResourceLocation<T> =
			TypedResourceLocation(T::class.java, namespace, path)
	}

	@Suppress("UNCHECKED_CAST")
	fun cast(obj: Any?): T? = obj as? T
}
