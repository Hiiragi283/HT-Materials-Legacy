package io.github.hiiragi283.material.api.part;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Table;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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

    public static boolean hasPart(@NotNull Item item, int meta) {
        return hasPart(new ItemWithMeta(item, meta));
    }

    private static boolean hasPart(@NotNull ItemWithMeta itemWithMeta) {
        return itemToPart.containsKey(itemWithMeta);
    }

    //    HTShapeKey, HTMaterialKey -> ItemWithMeta    //

    private static final Table<HTShapeKey, HTMaterialKey, ItemWithMeta> partToItem = HashBasedTable.create();

    @GroovyBlacklist
    @NotNull
    public static Table<HTShapeKey, HTMaterialKey, ItemWithMeta> getDefaultItemTable() {
        return ImmutableTable.copyOf(partToItem);
    }

    @GroovyBlacklist
    @Nullable
    public static ItemWithMeta getDefaultItem(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return partToItem.get(shapeKey, materialKey);
    }

    @NotNull
    public static ItemStack getDefaultStack(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return getDefaultStack(shapeKey, materialKey, 1);
    }

    @NotNull
    public static ItemStack getDefaultStack(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, int count) {
        ItemWithMeta itemWithMeta = getDefaultItem(shapeKey, materialKey);
        return itemWithMeta == null ? ItemStack.EMPTY : itemWithMeta.toStack(count);
    }

    public static boolean hasDefaultItem(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return partToItem.contains(shapeKey, materialKey);
    }

    //    HTShapeKey, HTMaterialKey -> Stream<ItemWithMeta>    //

    private static final Table<HTShapeKey, HTMaterialKey, Set<ItemWithMeta>> partToItems = HashBasedTable.create();

    @GroovyBlacklist
    @NotNull
    public static Table<HTShapeKey, HTMaterialKey, Set<ItemWithMeta>> getPartToItemsTable() {
        return ImmutableTable.copyOf(partToItems);
    }

    @NotNull
    public static Stream<ItemWithMeta> getItems(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return partToItems.contains(shapeKey, materialKey) ? partToItems.get(shapeKey, materialKey).stream() : Stream.empty();
    }

    @NotNull
    public static Stream<ItemStack> getItemStacks(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return getItemStacks(shapeKey, materialKey, 1);
    }

    @NotNull
    public static Stream<ItemStack> getItemStacks(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, int count) {
        return getItems(shapeKey, materialKey).map(itemWithMeta -> itemWithMeta.toStack(count));
    }

    //   Register    //

    public static void register(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, @NotNull ItemStack stack) {
        register(shapeKey, materialKey, stack.getItem(), stack.getMetadata());
    }

    public static void register(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, @NotNull Item item, int meta) {
        register(shapeKey, materialKey, new ItemWithMeta(item, meta));
    }

    @GroovyBlacklist
    public static void register(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, @NotNull ItemWithMeta itemWithMeta) {
        if (itemWithMeta.meta() == OreDictionary.WILDCARD_VALUE) {
            Item item = itemWithMeta.item();
            NonNullList<ItemStack> list = NonNullList.create();
            item.getSubItems(CreativeTabs.SEARCH, list);
            list.forEach(stack1 -> registerInternal(shapeKey, materialKey, new ItemWithMeta(stack1.getItem(), stack1.getMetadata())));
        } else registerInternal(shapeKey, materialKey, itemWithMeta);
    }

    private static void registerInternal(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey, @NotNull ItemWithMeta itemWithMeta) {
        HTPart part = new HTPart(shapeKey, materialKey);
        itemToPart.putIfAbsent(itemWithMeta, part);
        partToItem.put(shapeKey, materialKey, itemWithMeta);
    }

    @GroovyBlacklist
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
            register(part.shapeKey(), part.materialKey(), stack);
        }
    }

}