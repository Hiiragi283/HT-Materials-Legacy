package io.github.hiiragi283.common

import io.github.hiiragi283.api.HTLogger
import io.github.hiiragi283.api.HTMaterialsAPI
import io.github.hiiragi283.api.HTMaterialsPlugin
import io.github.hiiragi283.api.extension.runWhenOn
import io.github.hiiragi283.api.item.HTMaterialItem
import io.github.hiiragi283.api.item.HTMaterialItemProvider
import io.github.hiiragi283.api.material.HTMaterialKey
import io.github.hiiragi283.api.material.HTMaterialRegistry
import io.github.hiiragi283.api.part.HTPartManager
import io.github.hiiragi283.api.property.HTPropertyHolder
import io.github.hiiragi283.api.shape.HTShape
import io.github.hiiragi283.api.shape.HTShapeKey
import io.github.hiiragi283.api.shape.HTShapeRegistry
import io.github.hiiragi283.client.HTMaterialsClient
import io.github.hiiragi283.common.core.HTDefaultMaterials
import io.github.hiiragi283.common.core.HTMaterialsAPIImpl
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.*
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.oredict.OreDictionary

@Mod(
    modid = HTMaterialsAPI.MOD_ID,
    name = HTMaterialsAPI.MOD_NAME,
    version = HTMaterialsAPI.MOD_VERSION,
    acceptedMinecraftVersions = "[1.12,1.12.2]",
    modLanguageAdapter = "io.github.chaosunity.forgelin.KotlinAdapter"
)
object HTMaterials {

    @Mod.EventHandler
    private fun onConstruct(event: FMLConstructionEvent) {
        MinecraftForge.EVENT_BUS.register(this)
        runWhenOn(Side.CLIENT) {
            MinecraftForge.EVENT_BUS.register(HTMaterialsClient)
        }
        HTLogger.log { it.info("FMLConstructionEvent completed!") }
    }

    @SubscribeEvent
    fun onAddonRegister(event: HTMaterialsPlugin.RegisterEvent) {
        event.register(HTDefaultMaterials)
    }

    @Mod.EventHandler
    private fun preInit(event: FMLPreInitializationEvent) {
        HTLogger.log { it.info("=== List ===") }
        HTMaterialsAPI.instance.forEachAddon { plugin ->
            HTLogger.log { logger -> logger.info("${plugin::class.qualifiedName} - Priority: ${plugin.priority}") }
        }
        HTLogger.log { it.info("============") }

        HTMaterialsAPIImpl.shapeRegistry = HTShapeRegistry.build {
            HTMaterialsAPI.instance.forEachAddon { addon ->
                addon.registerShape(this)
            }
        }
        HTLogger.log { it.info("HTShapeRegistry built!") }

        HTMaterialsAPIImpl.materialRegistry = HTMaterialRegistry.create {
            HTMaterialsAPI.instance.forEachAddon { addon ->
                addon.registerMaterial(this)
            }
        }
        HTLogger.log { it.info("HTMaterialRegistry built!") }

        HTLogger.log { it.info("FMLPreInitializationEvent completed!") }
    }

    @SubscribeEvent
    fun onItemRegister(event: RegistryEvent.Register<Item>) {
        HTMaterialsAPIImpl.materialItemMap = buildMap {
            HTMaterialsAPI.instance.shapeRegistry.forEach shape@{ shapeKey: HTShapeKey, shape: HTShape ->
                val allowedKeys: List<HTMaterialKey> = buildList {
                    HTMaterialsAPI.instance.materialRegistry.forEach { materialKey: HTMaterialKey, material: HTPropertyHolder ->
                        if (shape.canGenerateItem(materialKey, material)) {
                            add(materialKey)
                        }
                    }
                }
                if (allowedKeys.isEmpty()) return@shape
                val item = HTMaterialItem(shapeKey, allowedKeys)
                event.registry.register(item)
                put(shapeKey, item)
            }
        }
        HTLogger.log { it.info("Material item map built!") }
    }

    @Mod.EventHandler
    private fun init(event: FMLInitializationEvent) {
        HTMaterialsAPI.instance.materialItemMap.forEach { (shapeKey: HTShapeKey, provider: HTMaterialItemProvider) ->
            provider.allowedKeys.forEach material@{ key ->
                val index: Int = HTMaterialsAPI.instance.materialRegistry.getIndex(key) ?: return@material
                OreDictionary.registerOre(shapeKey.getOreDict(key), provider.asItemStack(1, index))
            }
        }
        HTLogger.log { it.info("Ore Dictionary registered!") }

        HTLogger.log { it.info("FMLInitializationEvent completed!") }
    }

    @Mod.EventHandler
    private fun postInit(event: FMLPostInitializationEvent) {
        HTLogger.log { it.info("FMLPostInitializationEvent completed!") }
    }

    @Mod.EventHandler
    private fun onComplete(event: FMLLoadCompleteEvent) {
        HTMaterialsAPIImpl.partManager = HTPartManager.create(event)
        HTLogger.log { it.info("HTPartManager built!") }

        HTLogger.log { it.info("FMLLoadCompleteEvent completed!") }
    }

}