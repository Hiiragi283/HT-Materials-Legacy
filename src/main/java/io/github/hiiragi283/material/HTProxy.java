package io.github.hiiragi283.material;

import net.minecraftforge.fml.common.event.*;

public interface HTProxy {

    default void onConstruct(FMLConstructionEvent event) {
    }

    default void onPreInit(FMLPreInitializationEvent event) {
    }

    default void onInit(FMLInitializationEvent event) {
    }

    default void onPostInit(FMLPostInitializationEvent event) {
    }

    default void onComplete(FMLLoadCompleteEvent event) {
    }

}