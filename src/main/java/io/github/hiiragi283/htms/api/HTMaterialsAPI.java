package io.github.hiiragi283.htms.api;

import java.util.function.Consumer;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.extension.HTServiceLoaderUtil;
import io.github.hiiragi283.htms.api.material.HTMaterialRegistry;
import io.github.hiiragi283.htms.api.material.content.HTMaterialContentRegistry;
import io.github.hiiragi283.htms.api.shape.HTShapeRegistry;

public interface HTMaterialsAPI {

    @NotNull
    HTLogger LOGGER = new HTLogger(HMConstants.MOD_NAME);

    @NotNull
    HTMaterialsAPI INSTANCE = HTServiceLoaderUtil.getSingleton(HTMaterialsAPI.class);

    static @NotNull ResourceLocation getId(@NotNull String path) {
        return new ResourceLocation(HMConstants.MOD_ID, path);
    }

    @NotNull
    Item getIconItem();

    @NotNull
    Item getDictionaryItem();

    @NotNull
    CreativeTabs getCreativeTab();

    @NotNull
    HTShapeRegistry getShapeRegistry();

    @NotNull
    HTMaterialRegistry getMaterialRegistry();

    @NotNull
    HTMaterialContentRegistry getMaterialContentRegistry();

    void forEachPlugin(@NotNull Consumer<HTMaterialsPlugin> action);
}
