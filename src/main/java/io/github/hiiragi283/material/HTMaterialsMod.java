package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.HTMaterialsAddonManager;
import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.shape.HTShapes;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;

@SuppressWarnings("unused")
@Mod(modid = HMReference.MOD_ID, name = HMReference.MOD_NAME, version = HMReference.VERSION)
public final class HTMaterialsMod implements HTProxy {

    public static final Item ITEM_DUST = new HTMaterialItem(HTShapes.DUST);

    public static final Item ITEM_GEAR = new HTMaterialItem(HTShapes.GEAR);

    public static final Item ITEM_GEM = new HTMaterialItem(HTShapes.GEM);

    public static final Item ITEM_INGOT = new HTMaterialItem(HTShapes.INGOT);

    public static final Item ITEM_NUGGET = new HTMaterialItem(HTShapes.NUGGET);

    public static final Item ITEM_PLATE = new HTMaterialItem(HTShapes.PLATE);

    public static final Item ITEM_STICK = new HTMaterialItem(HTShapes.STICK);

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        HTMaterialsAddonManager.INSTANCE.onConstruct(event);
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        HTMaterialsAddonManager.INSTANCE.onPreInit(event);
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        HTMaterialsAddonManager.INSTANCE.onInit(event);
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
        HTMaterialsAddonManager.INSTANCE.onPostInit(event);
    }

    @Mod.EventHandler
    public void onComplete(FMLLoadCompleteEvent event) {
        HTMaterialsAddonManager.INSTANCE.onComplete(event);
    }

}