package io.github.hiiragi283.api.extension

import net.minecraft.util.ResourceLocation

data class TypedResourceLocation<T : Any>(val clazz: Class<T>, val id: ResourceLocation) {
    companion object {
        @JvmStatic
        inline fun <reified T : Any> of(id: ResourceLocation): TypedResourceLocation<T> =
            TypedResourceLocation(T::class.java, id)

        @JvmStatic
        inline fun <reified T : Any> of(namespace: String, path: String): TypedResourceLocation<T> =
            TypedResourceLocation(T::class.java, ResourceLocation(namespace, path))
    }

    @Suppress("UNCHECKED_CAST")
    fun cast(obj: Any?): T? = obj as? T
}