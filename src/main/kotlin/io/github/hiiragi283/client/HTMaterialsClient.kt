package io.github.hiiragi283.client

import io.github.hiiragi283.api.HTLogger
import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.extension.HTRuntimeResourcePack
import io.github.hiiragi283.api.item.HTMaterialItemColor
import io.github.hiiragi283.api.item.HTMaterialItemProvider
import io.github.hiiragi283.api.material.HTMaterialTooltipContext
import io.github.hiiragi283.api.material.HTMaterialType
import io.github.hiiragi283.api.material.property.HTMaterialProperties
import io.github.hiiragi283.api.shape.HTShapeKey
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.event.ColorHandlerEvent
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.event.entity.player.ItemTooltipEvent
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

@SideOnly(Side.CLIENT)
object HTMaterialsClient {

    @SubscribeEvent
    fun onModelRegister(event: ModelRegistryEvent) {
        HTMaterialsAPI.instance.materialItemMap.forEach { (shapeKey: HTShapeKey, provider: HTMaterialItemProvider) ->
            HTMaterialsAPI.instance.materialRegistry.forEachWithIndex { key, index, material ->
                if (index == null) return@forEachWithIndex
                val materialType: HTMaterialType = material.getOrDefault(
                    HTMaterialProperties.TYPE,
                    HTMaterialType.Solid
                )
                val modelId: ResourceLocation = HTMaterialsAPI.id("${shapeKey}/${key}")
                // Bind item and model
                ModelLoader.setCustomModelResourceLocation(
                    provider.asItem(),
                    index,
                    ModelResourceLocation(modelId, "inventory")
                )
                // Register item model
                HTRuntimeResourcePack.registerItemModel(modelId, materialType.createModel(shapeKey))
            }
        }
        HTLogger.log { it.info("Registered item models!") }
    }

    @SubscribeEvent
    fun onItemColored(event: ColorHandlerEvent.Item) {
        HTMaterialsAPI.instance.materialItemMap.values.forEach { provider ->
            event.itemColors.registerItemColorHandler(HTMaterialItemColor, provider.asItem())
        }
        HTLogger.log { it.info("Registered item color handlers!") }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    fun onItemTooltip(event: ItemTooltipEvent) {
        if (event.itemStack.isEmpty) return
        HTMaterialsAPI.instance.partManager[event.itemStack]
            ?.let { part ->
                HTMaterialTooltipContext(part.materialKey, part.material, part.shapeKey, event.itemStack)
                    .appendTooltips(event.toolTip)
            }
    }

}