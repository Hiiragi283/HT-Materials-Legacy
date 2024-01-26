package io.github.hiiragi283.material.api.part;

import java.util.*;
import java.util.stream.Stream;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.cleanroommc.groovyscript.api.GroovyBlacklist;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.materials.HTVanillaMaterials;
import io.github.hiiragi283.material.api.registry.HTNonNullTable;
import io.github.hiiragi283.material.api.registry.ItemWithMeta;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import io.github.hiiragi283.material.api.shape.HTShapes;

@Mod.EventBusSubscriber()
public abstract class HTPartDictionary {

    private static final Logger LOGGER = LogManager.getLogger(HTPartDictionary.class.getSimpleName());

    private HTPartDictionary() {}

    // ItemWithMeta -> HTPart //

    private static final Map<ItemWithMeta, HTPart> itemToPart = new HashMap<>();

    @Nullable
    public static HTPart getPart(@NotNull ItemStack stack) {
        return stack.isEmpty() ? null : getPart(stack.getItem(), stack.getMetadata());
    }

    @Nullable
    public static HTPart getPart(@NotNull Item item, int meta) {
        return ItemWithMeta.from(item, meta).map(itemToPart::get).filter(Objects::nonNull).findFirst().orElse(null);
    }

    public static boolean hasPart(@NotNull ItemStack stack) {
        return !stack.isEmpty() && hasPart(stack.getItem(), stack.getMetadata());
    }

    public static boolean hasPart(@NotNull Item item, int meta) {
        return ItemWithMeta.from(item, meta).map(itemToPart::containsKey).findFirst().orElse(false);
    }

    // HTShapeKey, HTMaterialKey -> ItemWithMeta //

    private static final Table<HTShapeKey, HTMaterialKey, ItemWithMeta> partToItem = HashBasedTable.create();

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
    public static ItemStack getDefaultStack(
                                            @NotNull HTShapeKey shapeKey,
                                            @NotNull HTMaterialKey materialKey,
                                            int count) {
        ItemWithMeta itemWithMeta = getDefaultItem(shapeKey, materialKey);
        return itemWithMeta == null ? ItemStack.EMPTY : itemWithMeta.toStack(count);
    }

    public static boolean hasDefaultItem(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return partToItem.contains(shapeKey, materialKey);
    }

    // HTShapeKey, HTMaterialKey -> Stream<ItemWithMeta> //

    private static final HTNonNullTable<HTShapeKey, HTMaterialKey, Set<ItemWithMeta>> partToItems = HTNonNullTable
            .create((shape, material) -> new HashSet<>());

    @NotNull
    public static Stream<ItemWithMeta> getItems(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return partToItems.getOrCreate(shapeKey, materialKey).stream();
    }

    @NotNull
    public static Stream<ItemStack> getItemStacks(@NotNull HTShapeKey shapeKey, @NotNull HTMaterialKey materialKey) {
        return getItemStacks(shapeKey, materialKey, 1);
    }

    @NotNull
    public static Stream<ItemStack> getItemStacks(
                                                  @NotNull HTShapeKey shapeKey,
                                                  @NotNull HTMaterialKey materialKey,
                                                  int count) {
        return getItems(shapeKey, materialKey).map(itemWithMeta -> itemWithMeta.toStack(count));
    }

    // Register //

    public static void register(
                                @NotNull HTShapeKey shapeKey,
                                @NotNull HTMaterialKey materialKey,
                                @NotNull ItemStack stack) {
        ItemWithMeta.fromStack(stack).forEach(itemWithMeta -> registerInternal(shapeKey, materialKey, itemWithMeta));
    }

    public static void register(
                                @NotNull HTShapeKey shapeKey,
                                @NotNull HTMaterialKey materialKey,
                                @NotNull Block block,
                                int meta) {
        ItemWithMeta.from(block, meta).forEach(itemWithMeta -> registerInternal(shapeKey, materialKey, itemWithMeta));
    }

    public static void register(
                                @NotNull HTShapeKey shapeKey,
                                @NotNull HTMaterialKey materialKey,
                                @NotNull Item item,
                                int meta) {
        ItemWithMeta.from(item, meta).forEach(itemWithMeta -> registerInternal(shapeKey, materialKey, itemWithMeta));
    }

    private static void registerInternal(
                                         @NotNull HTShapeKey shapeKey,
                                         @NotNull HTMaterialKey materialKey,
                                         @NotNull ItemWithMeta itemWithMeta) {
        if (itemWithMeta.isEmpty()) return;
        HTPart part = new HTPart(shapeKey, materialKey);
        itemToPart.putIfAbsent(itemWithMeta, part);
        if (partToItem.get(shapeKey, materialKey) == null) {
            partToItem.put(shapeKey, materialKey, itemWithMeta);
        }
        partToItems.getOrCreate(shapeKey, materialKey).add(itemWithMeta);
    }

    @GroovyBlacklist
    public static void reloadOreDicts() {
        LOGGER.info("Reloading Ore Dictionary...");
        Arrays.stream(OreDictionary.getOreNames()).forEach(oredict -> {
            OreDictionary.getOres(oredict).stream()
                    .filter(stack -> !hasPart(stack))
                    .map(stack -> new OreDictionary.OreRegisterEvent(oredict, stack))
                    .forEach(HTPartDictionary::onOreRegistered);
        });
        LOGGER.info("Reloaded Ore Dictionary!");

        LOGGER.info("Registering Custom Vanilla entries...");
        register(HTShapes.STONE, HTVanillaMaterials.STONE, Blocks.STONE, 0);
        register(HTShapes.STONE, HTVanillaMaterials.GRANITE, Blocks.STONE, 2);
        register(HTShapes.STONE, HTVanillaMaterials.DIORITE, Blocks.STONE, 4);
        register(HTShapes.STONE, HTVanillaMaterials.ANDESITE, Blocks.STONE, 6);
        register(HTShapes.STONE, HTVanillaMaterials.STONE, Blocks.COBBLESTONE, 0);
        register(HTShapes.SAND, HTVanillaMaterials.SAND, Blocks.SAND, OreDictionary.WILDCARD_VALUE);
        register(HTShapes.BLOCK, HTVanillaMaterials.SPONGE, Blocks.SPONGE, OreDictionary.WILDCARD_VALUE);
        register(HTShapes.STONE, HTVanillaMaterials.SAND, Blocks.SANDSTONE, OreDictionary.WILDCARD_VALUE);
        register(HTShapes.SLAB, HTVanillaMaterials.STONE, Blocks.STONE_SLAB, 0);
        register(HTShapes.SLAB, HTVanillaMaterials.SAND, Blocks.STONE_SLAB, 1);
        register(HTShapes.SLAB, HTVanillaMaterials.WOOD, Blocks.STONE_SLAB, 2);
        register(HTShapes.SLAB, HTVanillaMaterials.STONE, Blocks.STONE_SLAB, 3);
        register(HTShapes.SLAB, HTVanillaMaterials.BRICK, Blocks.STONE_SLAB, 4);
        register(HTShapes.SLAB, HTVanillaMaterials.STONE, Blocks.STONE_SLAB, 5);
        register(HTShapes.SLAB, HTVanillaMaterials.NETHER_BRICK, Blocks.STONE_SLAB, 6);
        register(HTShapes.SLAB, HTVanillaMaterials.QUARTZ, Blocks.STONE_SLAB, 7);
        register(HTShapes.BRICK, HTVanillaMaterials.BRICK, Blocks.BRICK_BLOCK, 0);
        register(HTShapes.STONE, HTVanillaMaterials.STONE, Blocks.MOSSY_COBBLESTONE, 0);
        register(HTShapes.STONE, HTVanillaMaterials.OBSIDIAN, Blocks.OBSIDIAN, 0);
        register(HTShapes.BLOCK, HTVanillaMaterials.ICE, Blocks.ICE, 0);
        register(HTShapes.BLOCK, HTVanillaMaterials.SNOW, Blocks.SNOW, 0);
        register(HTShapes.BLOCK, HTVanillaMaterials.CLAY, Blocks.CLAY, 0);
        register(HTShapes.STONE, HTVanillaMaterials.NETHERRACK, Blocks.NETHERRACK, 0);
        register(HTShapes.SAND, HTVanillaMaterials.SOUL_SAND, Blocks.SOUL_SAND, 0);
        register(HTShapes.BLOCK, HTVanillaMaterials.GLOWSTONE, Blocks.GLOWSTONE, 0);
        register(HTShapes.BRICK, HTVanillaMaterials.STONE, Blocks.STONEBRICK, OreDictionary.WILDCARD_VALUE);
        register(HTShapes.BRICK, HTVanillaMaterials.NETHER_BRICK, Blocks.NETHER_BRICK, 0);
        LOGGER.info("Registered Custom Vanilla entries!");
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
