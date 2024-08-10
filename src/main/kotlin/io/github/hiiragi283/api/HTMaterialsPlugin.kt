package io.github.hiiragi283.api

import io.github.hiiragi283.api.material.HTMaterialRegistry
import io.github.hiiragi283.api.shape.HTShapeRegistry
import net.minecraftforge.fml.common.eventhandler.Event

interface HTMaterialsPlugin {

    val modId: String
    val priority: Int

    fun registerShape(builder: HTShapeRegistry.Builder) {}

    fun registerMaterial(builder: HTMaterialRegistry.Builder) {}

    fun afterMaterialRegistration(instance: HTMaterialsAPI, isClient: Boolean) {}

    class RegisterEvent(private val list: MutableList<HTMaterialsPlugin>) : Event() {

        fun register(plugin: HTMaterialsPlugin) {
            list.add(plugin)
        }
    }

}