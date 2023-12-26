package io.github.hiiragi283.material.api.part;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import io.github.hiiragi283.material.api.item.ItemConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class HTPartManager {

    private HTPartManager() {
    }

    //    Item -> HTPart    //

    private static final Map<Item, HTPart> itemToPart = new HashMap<>();

    @Nullable
    public static HTPart getPart(ItemConvertible itemConvertible) {
        return itemToPart.get(itemConvertible.asItem());
    }

    public static boolean hasPart(ItemConvertible itemConvertible) {
        return itemToPart.containsKey(itemConvertible.asItem());
    }

    //    HTMaterialKey, HTShapeKey -> Item    //

    private static final Table<HTMaterialKey, HTShapeKey, Item> partToItem = HashBasedTable.create();

    public static Table<HTMaterialKey, HTShapeKey, Item> getDefaultItemTable() {
        return ImmutableTable.copyOf(partToItem);
    }

    @Nullable
    public static Item getDefaultItem(HTMaterialKey materialKey, HTShapeKey shapeKey) {
        return partToItem.get(materialKey, shapeKey);
    }

    public static boolean hasDefaultItem(HTMaterialKey materialKey, HTShapeKey shapeKey) {
        return partToItem.contains(materialKey, shapeKey);
    }

    //    HTMaterialKey, HTShapeKey -> Collection<Item>    //

    private static final Table<HTMaterialKey, HTShapeKey, Set<Item>> partToItems = HashBasedTable.create();

    public static Table<HTMaterialKey, HTShapeKey, Collection<Item>> getPartToItemsTable() {
        return ImmutableTable.copyOf(partToItems);
    }

    @NotNull
    public static Collection<Item> getItems(HTMaterialKey materialKey, HTShapeKey shapeKey) {
        return partToItems.contains(materialKey, shapeKey) ? partToItems.get(materialKey, shapeKey) : Collections.emptySet();
    }

}