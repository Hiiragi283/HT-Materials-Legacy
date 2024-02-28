package io.github.hiiragi283.api.item;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.material.HTMaterial;
import io.github.hiiragi283.api.material.HTMaterialKey;
import io.github.hiiragi283.api.part.HTPart;
import io.github.hiiragi283.api.shape.HTShapeKey;

public interface IMaterialItemProvider extends IItemProvider {

    @Nullable
    default HTMaterial getMaterial(@NotNull ItemStack stack) {
        return HTMaterialsAPI.INSTANCE.materialRegistry().getMaterial(stack.getMetadata());
    }

    @NotNull
    default HTMaterialKey getMaterialKey(@NotNull ItemStack stack) {
        HTMaterial material = getMaterial(stack);
        return material == null ? HTMaterialKey.EMPTY : material.key();
    }

    @NotNull
    HTShapeKey getShapeKey();

    @NotNull
    Stream<HTMaterial> getValidMaterials();

    @NotNull
    default Stream<HTMaterialKey> getMaterialKeys() {
        return getValidMaterials().map(HTMaterial::key);
    }

    @NotNull
    default IntStream getMaterialIndexes() {
        return getValidMaterials().mapToInt(HTMaterial::index);
    }

    @NotNull
    default Stream<ItemStack> getMaterialStacks() {
        return getMaterialIndexes().mapToObj(index -> new ItemStack(asItem(), 1, index));
    }

    @NotNull
    default HTPart getPart(@NotNull ItemStack stack) {
        return new HTPart(getShapeKey(), getMaterialKey(stack));
    }

    @NotNull
    default String getOreDict(@NotNull ItemStack stack) {
        return getShapeKey().getOreDict(getMaterialKey(stack));
    }
}
