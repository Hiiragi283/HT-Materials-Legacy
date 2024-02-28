package io.github.hiiragi283;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import io.github.hiiragi283.api.HTMaterialsAPI;
import io.github.hiiragi283.api.material.HTMaterialRegistry;
import io.github.hiiragi283.api.part.HTPartManager;
import io.github.hiiragi283.api.shape.HTShapeRegistry;

public final class HTMaterialsAPIImpl implements HTMaterialsAPI {

    static HTShapeRegistry shapeRegistry;
    static HTMaterialRegistry materialRegistry;
    static CreativeTabs creativeTabs;
    static Item iconItem;
    static Item dictionaryItem;
    static HTPartManager partManager;

    @Override
    public HTShapeRegistry shapeRegistry() {
        return shapeRegistry;
    }

    @Override
    public HTMaterialRegistry materialRegistry() {
        return materialRegistry;
    }

    @Override
    public CreativeTabs creativeTab() {
        return creativeTabs;
    }

    @Override
    public Item iconItem() {
        return iconItem;
    }

    @Override
    public Item dictionaryItem() {
        return dictionaryItem;
    }

    @Override
    public HTPartManager partManager() {
        return partManager;
    }
}
