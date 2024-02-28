package io.github.hiiragi283;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.registries.IForgeRegistry;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.HTMaterialsAddon;
import io.github.hiiragi283.api.extension.ServiceLoaderUtil;
import io.github.hiiragi283.api.material.HTMaterial;
import io.github.hiiragi283.api.material.HTMaterialHelper;
import io.github.hiiragi283.api.material.HTMaterialKey;
import io.github.hiiragi283.api.material.HTMaterialRegistry;
import io.github.hiiragi283.api.material.composition.HTMaterialComposition;
import io.github.hiiragi283.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.api.part.HTPartManager;
import io.github.hiiragi283.api.shape.*;

final class HTMaterialsCore {

    private static Iterable<HTMaterialsAddon> addons;

    public static void initAddons() {
        addons = ServiceLoaderUtil.getInstances(HTMaterialsAddon.class)
                .filter(addon -> Loader.isModLoaded(addon.getModId()))
                .sorted(Comparator.comparing(HTMaterialsAddon::getPriority)
                        .thenComparing(addon -> addon.getClass().getCanonicalName()))
                .collect(Collectors.toList());
        // Print sorted addons
        HTMaterialsAPI.LOGGER.info("HTMaterialsAddon collected!");
        HTMaterialsAPI.LOGGER.info("=== List ===");
        addons.forEach(addon -> HTMaterialsAPI.LOGGER.info("{} - Priority: {}", addon.getClass().getCanonicalName(),
                addon.getPriority()));
        HTMaterialsAPI.LOGGER.info("============");
    }

    // PreInit

    public static void registerShapes() {
        // Create and register shape
        HTShapeHelper shapeHelper = new HTShapeHelper();
        addons.forEach(addon -> addon.registerShape(shapeHelper));
        ImmutableMap.Builder<HTShapeKey, HTShape> builder = ImmutableMap.builder();
        shapeHelper.getKeys().forEach((HTShapeKey key) -> {
            Collection<HTShapeItemBuilder> itemBuilder = shapeHelper.getShapeItems(key);
            builder.put(key, new HTShape(key, Collections.unmodifiableCollection(itemBuilder)));
        });
        HTMaterialsAPIImpl.shapeRegistry = new HTShapeRegistry(builder.build());
    }

    public static void registerMaterials() {
        // Create and register material
        HTMaterialHelper materialHelper = new HTMaterialHelper();
        addons.forEach(addon -> addon.registerMaterial(materialHelper));
        ImmutableMap.Builder<HTMaterialKey, HTMaterial> builder = ImmutableMap.builder();
        ImmutableMap.Builder<Integer, HTMaterial> indexBuilder = ImmutableMap.builder();
        materialHelper.getKeyMap().forEach((HTMaterialKey key, Integer index) -> {
            HTMaterialComposition composition = materialHelper.getComposition(key);
            HTMaterialFlagSet flagSet = materialHelper.flagSet(key).build();
            HTMaterialPropertyMap propertyMap = materialHelper.propertyMap(key).build();
            HTMaterial material = new HTMaterial(key, index, composition, flagSet, propertyMap);
            builder.put(key, material);
            indexBuilder.put(index, material);
        });
        HTMaterialRegistry registry = new HTMaterialRegistry(builder.build(), indexBuilder.build());
        // Verify flag and property
        registry.getValues().forEach(material -> material.forEachFlag(flag -> flag.verify(material)));
        registry.getValues().forEach(material -> material.forEachProperty(prop -> prop.verify(material)));
        HTMaterialsAPIImpl.materialRegistry = registry;
    }

    // Init
    public static void initShapeItemBuilders(@NotNull IForgeRegistry<Item> registry) {
        HTMaterialsAPI.INSTANCE.shapeRegistry().getValues()
                .forEach(shape -> shape.itemBuilders().forEach(itemBuilder -> itemBuilder.initItem(registry)));
    }

    public static void bindItemToPart() {
        HTPartManager.Builder builder = new HTPartManager.Builder();
        addons.forEach(addon -> addon.bindItemToPart(builder));
        HTMaterialsAPIImpl.partManager = new HTPartManager(builder);
    }
}
