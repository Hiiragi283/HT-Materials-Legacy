package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.api.part.HTPart;
import io.github.hiiragi283.material.api.part.HTPartManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber()
public abstract class HMEventHandler {

    private HMEventHandler() {
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        var registry = event.getRegistry();
        HTMaterialItem.getItems().forEach(registry::register);
    }

    @Mod.EventBusSubscriber(value = Side.CLIENT)
    public static abstract class Client {

        private Client() {
        }

        @SubscribeEvent
        public static void onItemColored(ColorHandlerEvent.Item event) {
            HTMaterialItem.getItems().forEach(item -> event.getItemColors().registerItemColorHandler(
                    (stack, tintIndex) -> Optional.ofNullable(item.getMaterial(stack)).map(HTMaterial::getColor).map(Color::getRGB).orElse(-1),
                    item
            ));
        }

        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            HTMaterialItem.getItems().forEach(item -> item.getMaterialIndexes().forEach(index -> ModelLoader.setCustomModelResourceLocation(item, index, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"))));
        }

        @SubscribeEvent
        public static void onItemTooltip(ItemTooltipEvent event) {
            HTPart part = HTPartManager.getPart(event.getItemStack());
            if (part == null) return;
            HTMaterialUtils.addInformation(part.getMaterial(), part.getShape(), event.getItemStack(), event.getToolTip());
        }

    }

}