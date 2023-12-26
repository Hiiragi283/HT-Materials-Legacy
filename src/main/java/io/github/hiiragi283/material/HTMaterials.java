package io.github.hiiragi283.material;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = HMReference.MOD_ID, name = HMReference.MOD_NAME, version = HMReference.VERSION)
public final class HTMaterials implements HTLoader {

    public static final Logger LOGGER = LogManager.getLogger(HMReference.MOD_NAME);

    @SidedProxy(serverSide = "io.github.hiiragi283.material.HMCommonProxy", clientSide = "io.github.hiiragi283.material.HMCommonProxy$Client")
    public static HMCommonProxy proxy;

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        proxy.onConstruct(event);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        proxy.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        proxy.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        proxy.onPostInit(event);
    }

    @Mod.EventHandler
    public void onComplete(FMLLoadCompleteEvent event) {
        proxy.onComplete(event);
    }

}