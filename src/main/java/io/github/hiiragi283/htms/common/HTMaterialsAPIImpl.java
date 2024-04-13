package io.github.hiiragi283.htms.common;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.HTMaterialsAPI;
import io.github.hiiragi283.htms.api.HTMaterialsPlugin;
import io.github.hiiragi283.htms.api.extension.HTServiceLoaderUtil;
import io.github.hiiragi283.htms.api.material.HTMaterialRegistry;
import io.github.hiiragi283.htms.api.material.content.HTMaterialContentRegistry;
import io.github.hiiragi283.htms.api.shape.HTShapeRegistry;

public class HTMaterialsAPIImpl implements HTMaterialsAPI {

    private static final Iterable<HTMaterialsPlugin> plugins = HTServiceLoaderUtil.getInstances(HTMaterialsPlugin.class)
            .filter(plugin -> Loader.isModLoaded(plugin.getModId()))
            .sorted(Comparator.comparingInt(HTMaterialsPlugin::getPriority)
                    .thenComparing(plugin -> plugin.getClass().getCanonicalName()))
            .collect(Collectors.toCollection(LinkedHashSet::new));

    static Item iconItem;

    @Override
    public @NotNull Item getIconItem() {
        return iconItem;
    }

    static Item dictionaryItem;

    @Override
    public @NotNull Item getDictionaryItem() {
        return dictionaryItem;
    }

    private static final CreativeTabs creativeTabs = new CreativeTabs("") {

        @Override
        public @NotNull ItemStack createIcon() {
            return new ItemStack(iconItem);
        }
    };

    @Override
    public @NotNull CreativeTabs getCreativeTab() {
        return creativeTabs;
    }

    static HTShapeRegistry shapeRegistry;

    @Override
    public @NotNull HTShapeRegistry getShapeRegistry() {
        return shapeRegistry;
    }

    static HTMaterialRegistry materialRegistry;

    @Override
    public @NotNull HTMaterialRegistry getMaterialRegistry() {
        return materialRegistry;
    }

    static HTMaterialContentRegistry materialContentRegistry;

    @Override
    public @NotNull HTMaterialContentRegistry getMaterialContentRegistry() {
        return materialContentRegistry;
    }

    @Override
    public void forEachPlugin(@NotNull Consumer<HTMaterialsPlugin> action) {
        plugins.forEach(action);
    }
}
