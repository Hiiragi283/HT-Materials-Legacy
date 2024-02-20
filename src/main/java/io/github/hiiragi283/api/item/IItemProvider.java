package io.github.hiiragi283.api.item;

import net.minecraft.item.Item;

@FunctionalInterface
public interface IItemProvider {

    Item asItem();
}
