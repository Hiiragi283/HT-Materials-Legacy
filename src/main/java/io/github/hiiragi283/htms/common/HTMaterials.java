package io.github.hiiragi283.htms.common;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import io.github.hiiragi283.htms.api.HMConstants;
import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.extension.HTSideUtil;
import io.github.hiiragi283.htms.api.item.IMaterialItemProvider;
import io.github.hiiragi283.htms.api.material.HTMaterialRegistry;
import io.github.hiiragi283.htms.api.material.content.HTMaterialContentRegistry;
import io.github.hiiragi283.htms.api.shape.HTShape;
import io.github.hiiragi283.htms.api.shape.HTShapeRegistry;
import io.github.hiiragi283.htms.client.HTMaterialsClient;

@Mod(
     modid = HMConstants.MOD_ID,
     name = HMConstants.MOD_NAME,
     version = HMConstants.VERSION,
     acceptedMinecraftVersions = "[1.12.2, 1.13)")
public final class HTMaterials {

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        HTSideUtil.runWhenOn(Side.CLIENT, () -> MinecraftForge.EVENT_BUS.register(HTMaterialsClient.INSTANCE));

        HTMaterialsAPI.LOGGER.info("=== List ===");
        HTMaterialsAPI.INSTANCE.forEachPlugin(plugin -> HTMaterialsAPI.LOGGER
                .info(plugin.getClass().getCanonicalName() + " - Priority: " + plugin.getPriority()));
        HTMaterialsAPI.LOGGER.info("============");
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        registerShapes();
        registerMaterials();
        registerMaterialContents();

        HTMaterialsAPIImpl.iconItem = new Item()
                .setCreativeTab(HTMaterialsAPI.INSTANCE.getCreativeTab())
                .setTranslationKey(HMConstants.MOD_ID + ".icon")
                .setRegistryName(HMConstants.MOD_ID, "icon");
        HTMaterialsAPIImpl.dictionaryItem = new Item()
                .setCreativeTab(HTMaterialsAPI.INSTANCE.getCreativeTab())
                .setTranslationKey(HMConstants.MOD_ID + ".material_dictionary")
                .setRegistryName(HMConstants.MOD_ID, "material_dictionary");
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {}

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {}

    // Pre Init //

    private void registerShapes() {
        HTShapeRegistry.Builder builder = new HTShapeRegistry.Builder();
        HTMaterialsAPI.INSTANCE.forEachPlugin(plugin -> plugin.registerShape(builder));
        HTMaterialsAPIImpl.shapeRegistry = new HTShapeRegistry(builder);
        HTMaterialsAPI.LOGGER.info("Registered shapes");
    }

    private void registerMaterials() {
        HTMaterialRegistry.Builder builder = new HTMaterialRegistry.Builder();
        HTMaterialsAPI.INSTANCE.forEachPlugin(plugin -> plugin.registerMaterial(builder));
        HTMaterialsAPIImpl.materialRegistry = new HTMaterialRegistry(builder);
        HTMaterialsAPI.LOGGER.info("Registered materials");
    }

    private void registerMaterialContents() {
        HTMaterialContentRegistry.Builder builder = new HTMaterialContentRegistry.Builder();
        HTMaterialsAPI.INSTANCE.forEachPlugin(plugin -> plugin.registerMaterialContent(builder));
        HTMaterialsAPIImpl.materialContentRegistry = new HTMaterialContentRegistry(builder);
        HTMaterialsAPI.LOGGER.info("Registered material contents");
    }

    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> event) {
        HTMaterialsAPI.INSTANCE.getMaterialContentRegistry().register(event);
        event.getRegistry().registerAll(
                HTMaterialsAPI.INSTANCE.getIconItem(),
                HTMaterialsAPI.INSTANCE.getDictionaryItem());
        HTMaterialsAPI.LOGGER.info("Registered items to forge registry");
    }

    private void registerOreDicts() {
        for (IMaterialItemProvider provider : HTMaterialsAPI.INSTANCE.getMaterialContentRegistry().values()) {
            HTShape shape = provider.getShape();
            provider.getMaterialKeys().forEach(key -> {
                key.getMaterialOptional()
                        .map(provider::materialToStack)
                        .ifPresent(stack -> OreDictionary.registerOre(shape.getOreDict(key), stack));
            });
        }
        HTMaterialsAPI.LOGGER.info("Registered Ore Dictionary to material items");
    }
}
