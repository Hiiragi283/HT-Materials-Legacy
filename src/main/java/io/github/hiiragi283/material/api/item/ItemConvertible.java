package io.github.hiiragi283.material.api.item;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemConvertible {

    @NotNull
    Item asItem();

    @NotNull
    static Item asItem(Object obj) {
        if (obj instanceof ItemConvertible) {
            return ((ItemConvertible) obj).asItem();
        }
        return Items.AIR;
    }

}