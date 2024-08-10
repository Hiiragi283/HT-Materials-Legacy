package io.github.hiiragi283.api

import io.github.hiiragi283.api.extension.runCatchAndLog
import io.github.hiiragi283.api.item.HTMaterialItemProvider
import io.github.hiiragi283.api.material.HTMaterialRegistry
import io.github.hiiragi283.api.part.HTPart
import io.github.hiiragi283.api.part.HTPartManager
import io.github.hiiragi283.api.shape.HTShapeKey
import io.github.hiiragi283.api.shape.HTShapeRegistry
import io.github.hiiragi283.common.core.HTMaterialsAPIImpl
import net.minecraft.util.ResourceLocation

interface HTMaterialsAPI {

    companion object {
        const val MOD_ID: String = "ht_materials"
        const val MOD_NAME: String = "HT Materials Legacy"
        const val MOD_VERSION: String = "0.0.1"

        @JvmStatic
        fun id(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)

        @JvmStatic
        val instance: HTMaterialsAPI = HTMaterialsAPIImpl
    }

    val materialRegistry: HTMaterialRegistry
    val shapeRegistry: HTShapeRegistry
    val partManager: HTPartManager

    val materialItemMap: Map<HTShapeKey, HTMaterialItemProvider>

    /*fun forEachPhasedMaterial(action: (HTPhasedMaterial) -> Unit) {
        materialRegistry.keys.forEach { materialKey ->
            HTFluidPhase.entries.map { phase ->
                HTPhasedMaterial(materialKey, phase)
            }.forEach { runCatchAndLog { action(it) } }
        }
    }*/

    fun forEachPart(action: (HTPart) -> Unit) {
        materialRegistry.keys.forEach { materialKey ->
            shapeRegistry.keys.map { shapeKey ->
                HTPart(materialKey, shapeKey)
            }.forEach { runCatchAndLog { action(it) } }
        }
    }

    fun forEachAddon(action: (HTMaterialsPlugin) -> Unit)

}