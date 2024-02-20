package io.github.hiiragi283.api.material.property;

import java.util.List;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.api.material.HTMaterial;
import io.github.hiiragi283.api.shape.HTShape;

public interface HTMaterialProperty<T extends HTMaterialProperty<T>> {

    @NotNull
    HTPropertyKey<T> getKey();

    void verify(HTMaterial material);

    default void addInformation(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack,
                                @NotNull List<String> tooltips) {}
}
