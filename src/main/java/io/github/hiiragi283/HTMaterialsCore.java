package io.github.hiiragi283;

import java.util.*;
import java.util.stream.Collectors;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
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

    public static void onItemRegister(@NotNull IForgeRegistry<Item> registry) {
        HTMaterialsAPI.INSTANCE.shapeRegistry().getValues()
                .stream().flatMap(shape -> shape.itemBuilders().stream())
                .forEach(itemBuilder -> itemBuilder.registerItem(registry));
        registry.registerAll(HTMaterialsAPI.INSTANCE.iconItem(), HTMaterialsAPI.INSTANCE.dictionaryItem());
    }

    private static void setModel(Item item) {
        Optional.ofNullable(item.getRegistryName())
                .ifPresent(location -> ModelLoader.setCustomModelResourceLocation(
                        item,
                        0,
                        new ModelResourceLocation(location, "inventory")));
    }

    public static void onModelRegister(ModelRegistryEvent event) {
        HTMaterialsAPI.INSTANCE.shapeRegistry().getValues()
                .stream().flatMap(HTShape::ItemBuilderStream)
                .forEach(HTShapeItemBuilder::registerModel);
        setModel(HTMaterialsAPI.INSTANCE.iconItem());
        setModel(HTMaterialsAPI.INSTANCE.dictionaryItem());
    }

    // Init

    public static void bindItemToPart() {
        HTPartManager.Builder builder = new HTPartManager.Builder();
        addons.forEach(addon -> addon.bindItemToPart(builder));
        HTMaterialsAPIImpl.partManager = new HTPartManager(builder);
    }
}
