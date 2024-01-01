package io.github.hiiragi283.material;

import io.github.hiiragi283.material.api.HTAddon;
import io.github.hiiragi283.material.api.HTMaterialsAddon;
import io.github.hiiragi283.material.api.item.HTMaterialItem;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.api.material.HTMaterials;
import io.github.hiiragi283.material.api.part.HTPartManager;
import io.github.hiiragi283.material.api.shape.HTShapes;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HMCommonProxy implements HTProxy {

    private static final Logger LOGGER = LogManager.getLogger(HMReference.MOD_NAME + "/Proxy");

    private static Collection<HTMaterialsAddon> cache;

    @NotNull
    public static Collection<HTMaterialsAddon> getAddons() throws IllegalAccessException {
        if (cache == null) {
            throw new IllegalAccessException("HTMaterialsAddon not yet collected!");
        }
        return cache;
    }

    //    Pre Init    //

    @Override
    public void onPreInit(FMLPreInitializationEvent event) {
        //Collect HTMaterialsAddon
        Stream.Builder<HTMaterialsAddon> builder = Stream.builder();
        event.getAsmData().getAll(HTAddon.class.getCanonicalName()).forEach(data -> {
            try {
                HTMaterialsAddon addon = Class.forName(data.getClassName()).asSubclass(HTMaterialsAddon.class).getConstructor().newInstance();
                if (Loader.isModLoaded(addon.getModId())) builder.add(addon);
            } catch (Exception e) {
                LOGGER.catching(e);
            }
        });
        cache = builder.build().sorted(Comparator.comparingInt(HTMaterialsAddon::getPriority)).collect(Collectors.toList());
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
    }

    //    Init    //

    @Override
    public void onInit(FMLInitializationEvent event) {
        for (HTMaterialItem materialItem : HTMaterialItem.getItems()) {
            HTMaterialUtils.getMaterialStacks(materialItem).forEach(stack -> OreDictionary.registerOre(materialItem.getOreDict(stack), stack));
        }
    }

    public static class Client extends HMCommonProxy {

    }

}