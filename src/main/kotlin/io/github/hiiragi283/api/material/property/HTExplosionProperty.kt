package io.github.hiiragi283.api.material.property

import net.minecraft.entity.item.EntityItem
import net.minecraft.world.World

enum class HTExplosionProperty(val power: Float) {
    LOW(2.0f),
    MIDDLE(4.0f),
    HIGH(6.0f),
    ;

    fun createExplosion(world: World, entity: EntityItem) {
        world.createExplosion(
            null,
            entity.posX,
            entity.posY,
            entity.posZ,
            power,
            false
        )
    }
}