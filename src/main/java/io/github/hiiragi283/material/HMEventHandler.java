package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.item.HTItemMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class HMEventHandler {

    private HMEventHandler() {
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        var registry = event.getRegistry();
        HTItemMaterial.getItems().forEach(registry::register);
    }

}