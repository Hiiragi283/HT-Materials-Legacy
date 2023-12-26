package io.github.hiiragi283.material.api.item;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.shape.HTShape;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface MaterialItemConvertible extends ItemConvertible {

    @NotNull
    default HTMaterialKey getMaterialKey(ItemStack stack) {
        return Optional.ofNullable(HTMaterial.getMaterialOrNull(stack.getMetadata())).map(HTMaterial::key).orElse(HTMaterialKey.EMPTY);
    }

    @NotNull
    HTShapeKey getShapeKey();

    default Stream<HTMaterial> getMaterials(HTShape shape) {
        return HTMaterial.getKeyRegistry().values().stream().filter(shape);
    }

    default Stream<HTMaterialKey> getMaterialKeys(HTShape shape) {
        return getMaterials(shape).map(HTMaterial::key);
    }

    default Stream<Integer> getMaterialIndexes(HTShape shape) {
        return getMaterials(shape).map(HTMaterial::index);
    }

    default Stream<ItemStack> getMaterialStacks(HTItemMaterial item) {
        return getMaterialIndexes(item.shape).map(index -> new ItemStack(item, 1, index));
    }

}