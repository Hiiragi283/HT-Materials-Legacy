package io.github.hiiragi283.material.api.item;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.part.HTPart;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public interface MaterialItemConvertible {

    @Nullable
    default HTMaterial getMaterial(ItemStack stack) {
        return HTMaterial.getMaterialOrNull(stack.getMetadata());
    }

    @NotNull
    default HTMaterialKey getMaterialKey(ItemStack stack) {
        HTMaterial material = getMaterial(stack);
        return material == null ? HTMaterialKey.EMPTY : material.key();
    }

    @NotNull
    HTShapeKey getShapeKey();

    default Stream<HTMaterial> getMaterials() {
        return HTMaterial.getMaterials().filter(getShapeKey().getShape());
    }

    default Stream<HTMaterialKey> getMaterialKeys() {
        return getMaterials().map(HTMaterial::key);
    }

    default Stream<Integer> getMaterialIndexes() {
        return getMaterials().map(HTMaterial::index);
    }

    default Stream<ItemStack> getMaterialStacks() {
        return this instanceof Item item ? getMaterialIndexes().map(index -> new ItemStack(item, 1, index)) : Stream.empty();
    }

    default HTPart getPart(ItemStack stack) {
        return new HTPart(getShapeKey(), getMaterialKey(stack));
    }

    default String getOreDict(ItemStack stack) {
        return getShapeKey().getOreDict(getMaterialKey(stack));
    }

}