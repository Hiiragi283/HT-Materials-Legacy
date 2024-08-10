package io.github.hiiragi283.common.core

import io.github.hiiragi283.api.HTLogger
import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.HTMaterialsPlugin
import io.github.hiiragi283.api.item.HTMaterialItemProvider
import io.github.hiiragi283.api.material.HTMaterialRegistry
import io.github.hiiragi283.api.part.HTPartManager
import io.github.hiiragi283.api.shape.HTShapeKey
import io.github.hiiragi283.api.shape.HTShapeRegistry
import net.minecraftforge.common.MinecraftForge

internal object HTMaterialsAPIImpl : HTMaterialsAPI {

    override var materialRegistry: HTMaterialRegistry = HTMaterialRegistry.EMPTY
        internal set
    override var shapeRegistry: HTShapeRegistry = HTShapeRegistry.EMPTY
        internal set
    override var partManager: HTPartManager = HTPartManager.EMPTY
        internal set
    override var materialItemMap: Map<HTShapeKey, HTMaterialItemProvider> = emptyMap()
        internal set

    private val addons: Iterable<HTMaterialsPlugin> by lazy {
        buildList {
            MinecraftForge.EVENT_BUS.post(HTMaterialsPlugin.RegisterEvent(this))
            HTLogger.log { it.info("RegisterEvent fired!") }
        }
    }

    override fun forEachAddon(action: (HTMaterialsPlugin) -> Unit) {
        addons.forEach(action)
    }
}