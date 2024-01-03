package io.github.hiiragi283.material.api.part;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
import io.github.hiiragi283.material.api.item.IItemConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.registry.ItemWithMeta;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Stream;

@Mod.EventBusSubscriber()
public abstract class HTPartManager {

    private static final Logger LOGGER = LogManager.getLogger(HTPartManager.class.getSimpleName());

    private HTPartManager() {
    }

    //    ItemWithMeta -> HTPart    //

    private static final Map<ItemWithMeta, HTPart> itemToPart = new HashMap<>();

    @Nullable
    public static HTPart getPart(@NotNull ItemStack stack) {
        return stack.isEmpty() ? null : getPart(stack.getItem(), stack.getMetadata());
    }

    @Nullable
    public static HTPart getPart(@NotNull IItemConvertible convertible, int meta) {
        return getPart(convertible.asItem(), meta);
    }

    @Nullable
    public static HTPart getPart(@NotNull Item item, int meta) {
        return getPart(new ItemWithMeta(item, meta));
    }

    @Nullable
    private static HTPart getPart(@NotNull ItemWithMeta itemWithMeta) {
        return itemToPart.get(itemWithMeta);
    }

    public static boolean hasPart(@NotNull ItemStack stack) {
        return !stack.isEmpty() && hasPart(stack.getItem(), stack.getMetadata());
    }

    public static boolean hasPart(@NotNull IItemConvertible convertible, int meta) {
        return hasPart(convertible.asItem(), meta);
    }

    public static boolean hasPart(@NotNull Item item, int meta) {
        return hasPart(new ItemWithMeta(item, meta));
    }

    private static boolean hasPart(@NotNull ItemWithMeta itemWithMeta) {
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

    @NotNull
    public static ItemStack getDefaultStack(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey) {
        return getDefaultStack(materialKey, shapeKey, 1);
    }

    @NotNull
    public static ItemStack getDefaultStack(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey, int count) {
        ItemWithMeta itemWithMeta = getDefaultItem(materialKey, shapeKey);
        return itemWithMeta == null ? ItemStack.EMPTY : itemWithMeta.toStack(count);
    }

    public static boolean hasDefaultItem(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey) {
        return partToItem.contains(materialKey, shapeKey);
    }

    //    HTMaterialKey, HTShapeKey -> Stream<ItemWithMeta>    //

    private static final Table<HTMaterialKey, HTShapeKey, Set<ItemWithMeta>> partToItems = HashBasedTable.create();

    @NotNull
    public static Table<HTMaterialKey, HTShapeKey, Collection<ItemWithMeta>> getPartToItemsTable() {
        return ImmutableTable.copyOf(partToItems);
    }

    @NotNull
    public static Stream<ItemWithMeta> getItems(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey) {
        return partToItems.contains(materialKey, shapeKey) ? partToItems.get(materialKey, shapeKey).stream() : Stream.empty();
    }

    @NotNull
    public static Stream<ItemStack> getItemStacks(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey) {
        return getItemStacks(materialKey, shapeKey, 1);
    }

    @NotNull
    public static Stream<ItemStack> getItemStacks(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey, int count) {
        return getItems(materialKey, shapeKey).map(itemWithMeta -> itemWithMeta.toStack(count));
    }

    //   Register    //

    static void register(@NotNull HTMaterialKey materialKey, @NotNull HTShapeKey shapeKey, @NotNull ItemStack stack) {
        if (stack.isEmpty()) return;
        if (stack.getMetadata() == OreDictionary.WILDCARD_VALUE) {
            Item item = stack.getItem();
            NonNullList<ItemStack> list = NonNullList.create();
            item.getSubItems(CreativeTabs.SEARCH, list);
            list.forEach(stack1 -> register(materialKey, shapeKey, stack1.getItem(), stack1.getMetadata()));
        } else register(materialKey, shapeKey, stack.getItem(), stack.getMetadata());
    }

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

    public static void reloadOreDicts() {
        LOGGER.info("Reloading Ore Dictionary...");
        Arrays.stream(OreDictionary.getOreNames()).forEach(oredict -> {
            OreDictionary.getOres(oredict).stream()
                    .filter(stack -> !hasPart(stack))
                    .map(stack -> new OreDictionary.OreRegisterEvent(oredict, stack))
                    .forEach(HTPartManager::onOreRegistered);
        });
        LOGGER.info("Reloaded Ore Dictionary!");
    }

    @SubscribeEvent
    public static void onOreRegistered(OreDictionary.OreRegisterEvent event) {
        String oreDict = event.getName();
        ItemStack stack = event.getOre();
        LOGGER.info("OreDict Name: " + oreDict);
        HTPart part = HTPart.of(oreDict);
        if (part != null) {
            LOGGER.info("Part find!");
            register(part.materialKey(), part.shapeKey(), stack);
        }
    }

}