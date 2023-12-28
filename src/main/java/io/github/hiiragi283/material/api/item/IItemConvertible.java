package io.github.hiiragi283.material.api.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface IItemConvertible {

    @NotNull
    Item asItem();

    @NotNull
    static Item asItem(Object obj) {
        if (obj instanceof IItemConvertible iItemConvertible) {
            return iItemConvertible.asItem();
        }
        return Items.AIR;
    }

}