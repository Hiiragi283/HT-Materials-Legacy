package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.shape.HTShapes;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = HMReference.MOD_ID, name = HMReference.MOD_NAME, version = HMReference.VERSION)
public final class HTMaterialsMod implements HTProxy {

    public static final Item ITEM_DUST = new HTMaterialItem(HTShapes.DUST);

    public static final Item ITEM_GEAR = new HTMaterialItem(HTShapes.GEAR);

    public static final Item ITEM_GEM = new HTMaterialItem(HTShapes.GEM);

    public static final Item ITEM_INGOT = new HTMaterialItem(HTShapes.INGOT);

    public static final Item ITEM_NUGGET = new HTMaterialItem(HTShapes.NUGGET);

    public static final Item ITEM_PLATE = new HTMaterialItem(HTShapes.PLATE);

    public static final Item ITEM_STICK = new HTMaterialItem(HTShapes.STICK);

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