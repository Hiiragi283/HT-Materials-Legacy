package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.item.ItemMaterialHT;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Objects;

@Mod.EventBusSubscriber()
public abstract class HMEventHandler {

    private HMEventHandler() {
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        var registry = event.getRegistry();
        ItemMaterialHT.getItems().forEach(registry::register);
    }

    @Mod.EventBusSubscriber(value = Side.CLIENT)
    public static abstract class Client {

        private Client() {
        }

        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            ItemMaterialHT.getItems().forEach(item -> item.getMaterialIndexes().forEach(index -> ModelLoader.setCustomModelResourceLocation(item, index, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"))));
        }

        @SubscribeEvent
        public static void onItemTooltip(ItemTooltipEvent event) {

        }

    }

}