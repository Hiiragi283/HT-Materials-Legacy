package io.github.hiiragi283.api.shape

import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.material.property.HTMaterialProperties
import io.github.hiiragi283.api.property.HTPropertyHolder
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Event

class HTShapeBuilder private constructor(
    private val key: HTShapeKey,
    val allowItemGen: Boolean,
) {
    companion object {
        internal fun createBlock(key: HTShapeKey): HTShapeBuilder =
            HTShapeBuilder(key, false)

        internal fun createItem(key: HTShapeKey): HTShapeBuilder =
            HTShapeBuilder(key, true)
    }

    private val blackList: MutableList<HTMaterialKey> = mutableListOf()

    fun addBlacklist(vararg keys: HTMaterialKey): HTShapeBuilder = apply {
        keys.forEach(blackList::add)
    }

    internal fun build(): HTShape {
        MinecraftForge.EVENT_BUS.post(BuildingEvent(this))
        return HTShape { materialKey: HTMaterialKey, material: HTPropertyHolder ->
            allowItemGen && materialKey !in this.blackList && key in material.getOrDefault(
                HTMaterialProperties.ITEM_SET,
                emptySet(),
            )
        }
    }

    //    Event    //

    class BuildingEvent(val builder: HTShapeBuilder) : Event()
}