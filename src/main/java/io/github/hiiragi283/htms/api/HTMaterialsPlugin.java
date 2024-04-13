package io.github.hiiragi283.htms.api;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.htms.api.material.HTMaterialRegistry;
import io.github.hiiragi283.htms.api.material.content.HTMaterialContentRegistry;
import io.github.hiiragi283.htms.api.shape.HTShapeRegistry;

public interface HTMaterialsPlugin {

    @NotNull
    String getModId();

    default @NotNull ResourceLocation getId(@NotNull String path) {
        return new ResourceLocation(getModId(), path);
    }

    int getPriority();

    // Pre Init //

    default void registerShape(@NotNull HTShapeRegistry.Builder builder) {}

    default void registerMaterial(@NotNull HTMaterialRegistry.Builder builder) {}

    default void registerMaterialContent(@NotNull HTMaterialContentRegistry.Builder builder) {}
}
