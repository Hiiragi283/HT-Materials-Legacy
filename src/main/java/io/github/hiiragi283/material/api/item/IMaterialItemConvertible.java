package io.github.hiiragi283.material.api.item;

import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public interface IMaterialItemConvertible extends IItemConvertible {

    @NotNull
    default HTMaterialKey getMaterialKey(ItemStack stack) {
        return Optional.ofNullable(HTMaterial.getMaterialOrNull(stack.getMetadata())).map(HTMaterial::getKey).orElse(HTMaterialKey.EMPTY);
    }

    @NotNull
    HTShapeKey getShapeKey();

    default Stream<HTMaterial> getMaterials() {
        return HTMaterial.getRegistry().values().stream().filter(getShapeKey().getShape());
    }

    default Stream<HTMaterialKey> getMaterialKeys() {
        return getMaterials().map(HTMaterial::getKey);
    }

    default Stream<Integer> getMaterialIndexes() {
        return getMaterials().map(HTMaterial::getIndex);
    }

    default Stream<ItemStack> getMaterialStacks(ItemMaterialHT item) {
        return getMaterialIndexes().map(index -> new ItemStack(item, 1, index));
    }

}