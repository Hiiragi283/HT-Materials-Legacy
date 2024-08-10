package io.github.hiiragi283.api.material.property

import io.github.hiiragi283.api.extension.TypedResourceLocation
import net.minecraft.util.ResourceLocation

object HTMaterialFlags {
    private val registry: MutableMap<ResourceLocation, TypedResourceLocation<Unit>> = hashMapOf()

    @JvmStatic
    fun getOrCreateFlag(id: ResourceLocation): TypedResourceLocation<Unit> =
        registry.computeIfAbsent(id, TypedResourceLocation.Companion::of)
}