package hiiragi283.materials.common;

import hiiragi283.materials.api.HMReferences;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = HMReferences.MOD_ID, name = HMReferences.MOD_NAME, version = HMReferences.VERSION)
public final class HTMaterialsMod {

    private static final Logger LOGGER = LogManager.getLogger(HTMaterialsMod.class.getSimpleName());

    @Mod.EventHandler
    public static void onConstruct(FMLConstructionEvent event) {

        LOGGER.info("Succeeded construct event!");
    }

    @Mod.EventHandler
    public static void onPreInit(FMLPreInitializationEvent event) {
        HTMaterialRegistryImpl.registerMaterials();
        HTMaterialRegistryImpl.setupProperties();

        LOGGER.info("Succeeded pre-init event!");
    }

    @Mod.EventHandler
    public static void onInit(FMLInitializationEvent event) {

        LOGGER.info("Succeeded init event!");
    }

    @Mod.EventHandler
    public static void onPostInit(FMLPostInitializationEvent event) {

        LOGGER.info("Succeeded post-init event!");
    }
}
