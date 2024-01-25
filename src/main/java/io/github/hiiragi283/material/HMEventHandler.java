package io.github.hiiragi283.material;

import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.api.part.HTPart;
import io.github.hiiragi283.material.api.part.HTPartDictionary;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber()
public abstract class HMEventHandler {

    private HMEventHandler() {}

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        var registry = event.getRegistry();
        registry.register(HTMaterialsMod.ICON);
        HTMaterialItem.getItems().forEach(registry::register);
    }

    @Mod.EventBusSubscriber(value = Side.CLIENT)
    public static abstract class Client {

        private Client() {}

        @SubscribeEvent
        public static void onItemColored(ColorHandlerEvent.Item event) {
            HTMaterialItem.getItems().forEach(item -> event.getItemColors().registerItemColorHandler(
                    (stack, tintIndex) -> Optional.ofNullable(item.getMaterial(stack)).map(HTMaterial::color)
                            .map(Color::getRGB).orElse(-1),
                    item));
        }

        @SubscribeEvent
        public static void onModelRegister(ModelRegistryEvent event) {
            ModelLoader.setCustomModelResourceLocation(HTMaterialsMod.ICON, 0, new ModelResourceLocation(
                    Objects.requireNonNull(HTMaterialsMod.ICON.getRegistryName()), "inventory"));
            HTMaterialItem.getItems().forEach(item -> item.getMaterialIndexes()
                    .forEach(index -> ModelLoader.setCustomModelResourceLocation(item, index,
                            new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"))));
        }

        @SubscribeEvent
        public static void onItemTooltip(ItemTooltipEvent event) {
            ItemStack stack = event.getItemStack();
            if (stack.isEmpty()) return;

            List<String> tooltip = event.getToolTip();

            // Material Tooltips for Item
            HTPart part = HTPartDictionary.getPart(event.getItemStack());
            if (part != null) {
                HTMaterialUtils.addInformation(part.getMaterial(), part.getShape(), stack, tooltip);
            }

            // Material Tooltips for Fluid Container Item
            Optional.ofNullable(stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null))
                    .map(IFluidHandler::getTankProperties)
                    .map(properties -> properties[0])
                    .map(IFluidTankProperties::getContents)
                    .map(FluidStack::getFluid)
                    .map(Fluid::getName)
                    .map(HTMaterial::getMaterialOrNull)
                    .ifPresent(material -> HTMaterialUtils.addInformation(material, null, stack, tooltip));
            /*
             * .map(Arrays::asList)
             * .stream()
             * .flatMap(Collection::stream)
             * .map(IFluidTankProperties::getContents)
             * .filter(Objects::nonNull)
             * .map(FluidStack::getFluid)
             * .map(Fluid::getName)
             * .map(HTMaterial::getMaterialOrNull)
             * .filter(Objects::nonNull)
             * .forEach(material -> HTMaterialUtils.addInformation(material, null, stack, tooltip));
             */
        }
    }
}
