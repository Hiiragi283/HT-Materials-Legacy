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
    default HTMaterial getMaterial(ItemStack stack) {
        return HTMaterialsAPI.INSTANCE.materialRegistry().getMaterial(stack.getMetadata());
    }

    @NotNull
    default HTMaterialKey getMaterialKey(ItemStack stack) {
        HTMaterial material = getMaterial(stack);
        return material == null ? HTMaterialKey.EMPTY : material.key();
    }

    @NotNull
    HTShapeKey getShapeKey();

    default Stream<HTMaterial> getMaterials() {
        return HTMaterialsAPI.INSTANCE.materialRegistry().getValues().stream();
    }

    default Stream<HTMaterialKey> getMaterialKeys() {
        return getMaterials().map(HTMaterial::key);
    }

    default IntStream getMaterialIndexes() {
        return getMaterials().mapToInt(HTMaterial::index);
    }

    default Stream<ItemStack> getMaterialStacks() {
        return getMaterialIndexes().mapToObj(index -> new ItemStack(asItem(), 1, index));
    }

    default HTPart getPart(ItemStack stack) {
        return new HTPart(getShapeKey(), getMaterialKey(stack));
    }

    default String getOreDict(ItemStack stack) {
        return getShapeKey().getOreDict(getMaterialKey(stack));
    }
}
