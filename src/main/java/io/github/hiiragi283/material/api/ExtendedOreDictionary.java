package io.github.hiiragi283.material.api;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.oredict.OreDictionary;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.ItemWithMeta;
import io.github.hiiragi283.material.util.HTCollectors;

public class ExtendedOreDictionary {

    private static final List<String> idToName = new ArrayList<>();
    private static final Map<String, Integer> nameToId = new HashMap<>();
    private static final HTNonNullMap<Integer, Set<ItemWithMeta>> idToStack = HTNonNullMap
            .create(key -> new HashSet<>());
    private static final HTNonNullMap<ItemWithMeta, Set<Integer>> stackToId = HTNonNullMap
            .create(key -> new HashSet<>());
    private static final NonNullList<ItemStack> EMPTY_LIST = NonNullList.create();

    public static final String UNKNOWN_OREDICT = "Unknown";

    public static int getOreID(@NotNull String name) {
        Integer val = nameToId.get(name);
        if (val == null) {
            idToName.add(name);
            val = idToName.size() - 1;
            nameToId.put(name, val);
        }
        return val;
    }

    @NotNull
    public static String getOreName(int id) {
        return (id >= 0 && id < idToName.size()) ? idToName.get(id) : UNKNOWN_OREDICT;
    }

    public static int[] getOreIDs(@NotNull ItemStack stack) {
        return ItemWithMeta.fromStack(stack)
                .map(stackToId::getOrCreate)
                .flatMap(Collection::stream)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    @NotNull
    public static NonNullList<ItemStack> getOres(@NotNull String name) {
        return getOres(getOreID(name));
    }

    @NotNull
    public static NonNullList<ItemStack> getOres(@NotNull String name, boolean alwaysCreateEntry) {
        if (alwaysCreateEntry) {
            return getOres(getOreID(name));
        }
        return nameToId.containsKey(name) ? getOres(getOreID(name)) : EMPTY_LIST;
    }

    public static boolean doesOreNameExist(@NotNull String name) {
        return nameToId.containsKey(name);
    }

    @NotNull
    public static String[] getOreNames() {
        return idToName.toArray(new String[0]);
    }

    @NotNull
    private static NonNullList<ItemStack> getOres(int id) {
        return idToStack.getOrCreate(id).stream().map(ItemWithMeta::toStack).collect(HTCollectors.nonNullList());
    }

    public static void registerOre(@NotNull String name, @NotNull Item ore) {
        ItemWithMeta.from(ore, 0).forEach(itemWithMeta -> registerOreImpl(name, itemWithMeta));
    }

    public static void registerOre(@NotNull String name, @NotNull Block ore) {
        ItemWithMeta.from(ore, 0).forEach(itemWithMeta -> registerOreImpl(name, itemWithMeta));
    }

    public static void registerOre(@NotNull String name, @NotNull ItemStack stack) {
        ItemWithMeta.fromStack(stack).forEach(itemWithMeta -> registerOreImpl(name, itemWithMeta));
    }

    private static void registerOreImpl(@NotNull String name, @NotNull ItemWithMeta itemWithMeta) {
        if (name.equals(UNKNOWN_OREDICT)) return;
        if (name.isEmpty()) return;
        int oreId = getOreID(name);
        stackToId.getOrCreate(itemWithMeta).add(oreId);
        idToStack.getOrCreate(oreId).add(itemWithMeta);
        MinecraftForge.EVENT_BUS.post(new OreDictionary.OreRegisterEvent(name, itemWithMeta.toStack()));
    }

    public static void rebakeMap() {
        stackToId.forEach(((itemWithMeta, integers) -> integers.clear()));
        idToStack.forEach((integer, itemWithMetas) -> {
            Set<ItemWithMeta> ores = idToStack.getOrCreate(integer);
            for (ItemWithMeta itemWithMeta : ores) {
                ResourceLocation name = itemWithMeta.item.delegate.name();
                if (name == null) {
                    FMLLog.log.debug(
                            "Defaulting unregistered ore dictionary entry for ore dictionary {}: type {} to -1",
                            getOreName(integer), itemWithMeta.item.getClass());
                    continue;
                }
                Set<Integer> ids = stackToId.getOrCreate(itemWithMeta);
                ids.add(integer);
            }
        });
    }
}
