package io.github.hiiragi283.material;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.material.api.fluid.HTMaterialFluid;
import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialEvent;
import io.github.hiiragi283.material.api.material.property.fluid.HTFluidPropertyBase;
import io.github.hiiragi283.material.api.part.HTPartDictionary;
import io.github.hiiragi283.material.api.shape.HTShapeEvent;
import io.github.hiiragi283.material.api.shape.HTShapes;

@SuppressWarnings("unused")
@Mod(
     modid = HMReference.MOD_ID,
     name = HMReference.MOD_NAME,
     version = HMReference.VERSION,
     dependencies = "after:jei;" + "after:crafttweaker;" + "after:groovyscript;")
public final class HTMaterialsMod {

    private static final Logger LOGGER = LogManager.getLogger(HMReference.MOD_NAME);

    public static final CreativeTabs CREATIVE_TABS = new CreativeTabs(HMReference.MOD_ID) {

        @NotNull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ICON);
        }
    };

    public static final Item ICON = new Item()
            .setCreativeTab(CREATIVE_TABS)
            .setRegistryName(getId("icon"))
            .setTranslationKey(HMReference.MOD_ID);

    public static final Item ITEM_DUST = new HTMaterialItem(HTShapes.DUST);
    public static final Item ITEM_GEAR = new HTMaterialItem(HTShapes.GEAR);
    public static final Item ITEM_GEM = new HTMaterialItem(HTShapes.GEM);
    public static final Item ITEM_INGOT = new HTMaterialItem(HTShapes.INGOT);
    public static final Item ITEM_NUGGET = new HTMaterialItem(HTShapes.NUGGET);
    public static final Item ITEM_PLATE = new HTMaterialItem(HTShapes.PLATE);
    public static final Item ITEM_STICK = new HTMaterialItem(HTShapes.STICK);

    public static ResourceLocation getId(String path) {
        return new ResourceLocation(HMReference.MOD_ID, path);
    }

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        // Register HTShape and HTMaterial
        HTShapeEvent.init();
        LOGGER.info("HTShape initialized!");
        HTMaterialEvent.init();
        LOGGER.info("HTMaterial initialized!");
        // Reload Ore Dictionary
        HTPartDictionary.reloadOreDicts();
        // Register Material Fluids
        HTMaterial.getMaterials().forEach(material -> material
                .getProperties()
                .values()
                .stream()
                .filter(HTFluidPropertyBase.class::isInstance)
                .map(HTFluidPropertyBase.class::cast)
                .forEach(property -> {
                    HTMaterialFluid fluid = new HTMaterialFluid(material, property);
                    FluidRegistry.registerFluid(fluid);
                    FluidRegistry.addBucketForFluid(fluid);
                }));
        LOGGER.info("HTMaterialFluid initialized!");
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        for (HTMaterialItem materialItem : HTMaterialItem.getItems()) {
            materialItem.getMaterialStacks()
                    .forEach(stack -> OreDictionary.registerOre(materialItem.getOreDict(stack), stack));
        }
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {}

    @Mod.EventHandler
    public void onComplete(FMLLoadCompleteEvent event) {}
}
