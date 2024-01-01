package io.github.hiiragi283.material.api.item;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.HTMaterialUtils;
import io.github.hiiragi283.material.api.part.HTPart;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public interface IMaterialItemConvertible extends IItemConvertible {

    @NotNull
    default HTMaterialKey getMaterialKey(ItemStack stack) {
        HTMaterial material = HTMaterial.getMaterialOrNull(stack.getMetadata());
        return material == null ? HTMaterialKey.EMPTY : material.getKey();
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
        return new HTPart(getMaterialKey(stack), getShapeKey());
    }

    default String getOreDict(ItemStack stack) {
        return getShapeKey().getOreDict(getMaterialKey(stack));
    }

}