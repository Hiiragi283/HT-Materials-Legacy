package io.github.hiiragi283.bwms.api.material.property

import net.minecraft.core.entity.EntityItem
import net.minecraft.core.world.World

enum class HTExplosionProperty(val power: Float) {
	LOW(2.0f),
	MIDDLE(4.0f),
	HIGH(6.0f),
	;

	fun createExplosion(world: World, entity: EntityItem) {
		world.createExplosion(
			null,
			entity.x,
			entity.y,
			entity.z,
			power,
		)
	}
}
