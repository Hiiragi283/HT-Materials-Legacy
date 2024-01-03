package io.github.hiiragi283.material.api;

import io.github.hiiragi283.material.HTProxy;
import io.github.hiiragi283.material.api.fluid.HTMaterialFluid;
import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.api.material.HTMaterials;
import io.github.hiiragi283.material.api.material.materials.HTElementMaterials;
import io.github.hiiragi283.material.api.material.materials.HTVanillaMaterials;
import io.github.hiiragi283.material.api.material.property.HTFluidProperty;
import io.github.hiiragi283.material.api.material.property.HTPropertyKeys;
import io.github.hiiragi283.material.api.part.HTPartManager;
import io.github.hiiragi283.material.api.shape.HTShapes;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum HTMaterialsAddonManager implements HTProxy {

    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(HTMaterialsAddonManager.class.getSimpleName());

    private static final Stream.Builder<HTMaterialsAddon> cache = Stream.builder();

    private static Collection<HTMaterialsAddon> addons;

    @NotNull
    public static Collection<HTMaterialsAddon> getAddons() throws IllegalAccessException {
        if (addons == null) {
            throw new IllegalAccessException("HTMaterialsAddon not yet collected!");
        }
        return addons;
    }

    @SafeVarargs
    @Deprecated
    public static void register(Class<? extends HTMaterialsAddon>... classes) {
        Stream.Builder<HTMaterialsAddon> builder = Stream.builder();
        for (Class<? extends HTMaterialsAddon> clazz : classes) {
            try {
                builder.add(clazz.getConstructor().newInstance());
            } catch (Exception e) {
                LOGGER.throwing(e);
            }
        }
        register(builder.build());
    }

    public static void register(HTMaterialsAddon... addon) {
        register(Arrays.stream(addon));
    }

    private static void register(Stream<HTMaterialsAddon> addons) {
        if (Loader.instance().getLoaderState().compareTo(LoaderState.CONSTRUCTING) > 0) {
            throw new RuntimeException("HTMaterialsAddonManager#register must be called during FMLConstructionEvent!");
        }
        addons.forEach(cache::add);
    }

    //    HTProxy    //


    @Override
    public void onConstruct(FMLConstructionEvent event) {
        register(
                HTShapes.INSTANCE,
                HTElementMaterials.INSTANCE,
                HTVanillaMaterials.INSTANCE
        );
    }

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        addons = cache.build().sorted(Comparator.comparingInt(HTMaterialsAddon::getPriority)).collect(Collectors.toList());
        //Register HTShape and HTMaterial
        try {
            HTShapes.init();
            LOGGER.info("HTShape initialized!");
            HTMaterials.init();
            LOGGER.info("HTMaterial initialized!");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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

    @Override
    public void onInit(FMLInitializationEvent event) {
        for (HTMaterialItem materialItem : HTMaterialItem.getItems()) {
            HTMaterialUtils.getMaterialStacks(materialItem).forEach(stack -> OreDictionary.registerOre(materialItem.getOreDict(stack), stack));
        }
    }

}