package io.github.hiiragi283.bwms.api.fluid

import io.github.hiiragi283.bwms.api.BWMsAPI
import io.github.hiiragi283.bwms.api.property.HTPropertyHolder
import net.minecraft.core.data.registry.Registries
import net.minecraft.core.data.registry.Registry

class HTFluidManager {

	companion object {
		@JvmField
		val FLUID_GROUPS: Registry<List<HTPropertyHolder>> = Registry()

		init {
			Registries.getInstance().register("${BWMsAPI.MOD_ID}:fluid_groups", FLUID_GROUPS)
		}
	}

}
