package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.fluid.HTMaterialFluid;
import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.api.material.HTMaterials;
import io.github.hiiragi283.material.api.material.property.HTFluidProperty;
import io.github.hiiragi283.material.api.material.property.HTPropertyKeys;
import io.github.hiiragi283.material.api.part.HTPartManager;
import io.github.hiiragi283.material.api.shape.HTShapes;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
@Mod(modid = HMReference.MOD_ID, name = HMReference.MOD_NAME, version = HMReference.VERSION)
public final class HTMaterialsMod {

    private static final Logger LOGGER = LogManager.getLogger(HMReference.MOD_NAME);

    public static final Item ITEM_DUST = new HTMaterialItem(HTShapes.DUST);

    public static final Item ITEM_GEAR = new HTMaterialItem(HTShapes.GEAR);

    public static final Item ITEM_GEM = new HTMaterialItem(HTShapes.GEM);

    public static final Item ITEM_INGOT = new HTMaterialItem(HTShapes.INGOT);

    public static final Item ITEM_NUGGET = new HTMaterialItem(HTShapes.NUGGET);

    public static final Item ITEM_PLATE = new HTMaterialItem(HTShapes.PLATE);

    public static final Item ITEM_STICK = new HTMaterialItem(HTShapes.STICK);

    @Mod.EventHandler
    public void onConstruct(FMLConstructionEvent event) {
    }

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        //Register HTShape and HTMaterial
        HTShapes.init();
        LOGGER.info("HTShape initialized!");
        HTMaterials.init();
        LOGGER.info("HTMaterial initialized!");
        //Reload Ore Dictionary
        HTPartManager.reloadOreDicts();
        //Register Material Fluids
        for (HTMaterial material : HTMaterial.getRegistry().values()) {
            HTFluidProperty fluidProperty = material.getProperty(HTPropertyKeys.FLUID);
            if (fluidProperty == null) continue;
            FluidRegistry.registerFluid(new HTMaterialFluid(material, fluidProperty));
        }
        LOGGER.info("HTMaterialFluid initialized!");
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        for (HTMaterialItem materialItem : HTMaterialItem.getItems()) {
            HTMaterialUtils.getMaterialStacks(materialItem).forEach(stack -> OreDictionary.registerOre(materialItem.getOreDict(stack), stack));
        }
    }

    @Mod.EventHandler
    public void onPostInit(FMLPostInitializationEvent event) {
    }

    @Mod.EventHandler
    public void onComplete(FMLLoadCompleteEvent event) {
    }

}