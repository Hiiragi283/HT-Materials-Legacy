package io.github.hiiragi283.material.api.part;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import io.github.hiiragi283.material.api.item.IItemConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.registry.ItemWithMeta;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class HTPartManager {

    private HTPartManager() {
    }

    //    ItemWithMeta -> HTPart    //

    private static final Map<ItemWithMeta, HTPart> itemToPart = new HashMap<>();

    @Nullable
    public static HTPart getPart(@NotNull ItemWithMeta itemWithMeta) {
        return itemToPart.get(itemWithMeta);
    }

    public static boolean hasPart(@NotNull ItemWithMeta itemWithMeta) {
        return itemToPart.containsKey(itemWithMeta);
    }

    //    HTMaterialKey, HTShapeKey -> ItemWithMeta    //

    private static final Table<HTMaterialKey, HTShapeKey, ItemWithMeta> partToItem = HashBasedTable.create();

    @NotNull
    public static Table<HTMaterialKey, HTShapeKey, ItemWithMeta> getDefaultItemTable() {
        return ImmutableTable.copyOf(partToItem);
    }

    @Nullable
    public static ItemWithMeta getDefaultItem(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey) {
        return partToItem.get(materialKey, shapeKey);
    }

    public static boolean hasDefaultItem(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey) {
        return partToItem.contains(materialKey, shapeKey);
    }

    //    HTMaterialKey, HTShapeKey -> Collection<ItemWithMeta>    //

    private static final Table<HTMaterialKey, HTShapeKey, Set<ItemWithMeta>> partToItems = HashBasedTable.create();

    @NotNull
    public static Table<HTMaterialKey, HTShapeKey, Collection<ItemWithMeta>> getPartToItemsTable() {
        return ImmutableTable.copyOf(partToItems);
    }

    @NotNull
    public static Collection<ItemWithMeta> getItems(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey) {
        return partToItems.contains(materialKey, shapeKey) ? partToItems.get(materialKey, shapeKey) : Collections.emptySet();
    }

    //   Register    //

    static void register(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey, @NotNull IItemConvertible convertible, int meta) {
        register(materialKey, shapeKey, convertible.asItem(), meta);
    }

    static void register(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey, @NotNull Item item, int meta) {
        register(materialKey, shapeKey, new ItemWithMeta(item, meta));
    }

    static void register(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey, @NotNull ItemWithMeta itemWithMeta) {
        HTPart part = new HTPart(materialKey, shapeKey);
        itemToPart.putIfAbsent(itemWithMeta, part);
        partToItem.put(materialKey, shapeKey, itemWithMeta);

    }

}