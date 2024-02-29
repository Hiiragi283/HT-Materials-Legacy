package io.github.hiiragi283;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.item.ItemMaterialDictionary;

@Mod(modid = HMReference.MOD_ID,
     name = HMReference.MOD_NAME,
     version = HMReference.VERSION)
public final class HTMaterialsMod {

    @Mod.EventHandler
    public void onConstruct() {
        MinecraftForge.EVENT_BUS.register(this);
        HTMaterialsCore.initAddons();
    }

    @Mod.EventHandler
    public void onPreInit() {
        HTMaterialsCore.registerShapes();
        HTMaterialsCore.registerMaterials();
        HTMaterialsAPIImpl.creativeTabs = new CreativeTabs("material") {

            @NotNull
            @Override
            public ItemStack createIcon() {
                return new ItemStack(HTMaterialsAPI.INSTANCE.iconItem());
            }
        };
        HTMaterialsAPIImpl.iconItem = new Item()
                .setCreativeTab(HTMaterialsAPI.INSTANCE.creativeTab())
                .setRegistryName(HTMaterialsAPI.MOD_ID, "icon");
        HTMaterialsAPIImpl.dictionaryItem = ItemMaterialDictionary.INSTANCE;
    }

    @SubscribeEvent
    public void registerItem(RegistryEvent.Register<Item> event) {
        HTMaterialsCore.onItemRegister(event.getRegistry());
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onModelRegister(ModelRegistryEvent event) {
        HTMaterialsCore.onModelRegister(event);
    }

    @Mod.EventHandler
    public void onInit() {}

    @Mod.EventHandler
    public void onPostInit() {}
}
