package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.shape.HTShape;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface HTMaterialProperty<T extends HTMaterialProperty<T>> {

    @NotNull
    HTPropertyKey<T> getKey();


    void verify(HTMaterial material);

    default void addInformation(@NotNull HTMaterial material, @Nullable HTShape shape, @NotNull ItemStack stack, @NotNull List<String> tooltips) {
    }

}