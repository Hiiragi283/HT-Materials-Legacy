package io.github.hiiragi283.material.api.item;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.api.part.HTPart;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
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
        return HTMaterialUtils.getMaterials(getShapeKey());
    }

    default Stream<HTMaterialKey> getMaterialKeys() {
        return HTMaterialUtils.getMaterialKeys(getShapeKey());
    }

    default Stream<Integer> getMaterialIndexes() {
        return HTMaterialUtils.getMaterialIndexes(getShapeKey());
    }

    default Stream<ItemStack> getMaterialStacks(HTMaterialItem item) {
        return HTMaterialUtils.getMaterialStacks(item);
    }

    default HTPart getPart(ItemStack stack) {
        return new HTPart(getShapeKey(), getMaterialKey(stack));
    }

    default String getOreDict(ItemStack stack) {
        return getShapeKey().getOreDict(getMaterialKey(stack));
    }

}