package io.github.hiiragi283.material.api.material.materials;

import java.lang.reflect.Field;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialEvent;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.material.property.HTCompoundProperty;
import io.github.hiiragi283.material.api.material.property.HTFluidProperty;
import io.github.hiiragi283.material.api.material.property.HTMixtureProperty;
import io.github.hiiragi283.material.util.HTColor;

@Mod.EventBusSubscriber
public enum HTVanillaMaterials {

    INSTANCE;

    public static final HTMaterialKey STONE = new HTMaterialKey("stone", 1000);
    public static final HTMaterialKey GRANITE = new HTMaterialKey("granite", 1001);
    public static final HTMaterialKey DIORITE = new HTMaterialKey("diorite", 1002);
    public static final HTMaterialKey ANDESITE = new HTMaterialKey("andesite", 1003);
    public static final HTMaterialKey WOOD = new HTMaterialKey("wood", 1004);
    public static final HTMaterialKey BEDROCK = new HTMaterialKey("bedrock", 1005);
    public static final HTMaterialKey WATER = new HTMaterialKey("water", 1006);
    public static final HTMaterialKey LAVA = new HTMaterialKey("lava", 1007);
    public static final HTMaterialKey SAND = new HTMaterialKey("sand", 1008);
    public static final HTMaterialKey COAL = new HTMaterialKey("coal", 1009);
    public static final HTMaterialKey SPONGE = new HTMaterialKey("sponge", 1010);
    public static final HTMaterialKey GLASS = new HTMaterialKey("glass", 1011);
    public static final HTMaterialKey LAPIS = new HTMaterialKey("lapis", 1012);
    public static final HTMaterialKey BRICK = new HTMaterialKey("brick", 1013);
    public static final HTMaterialKey OBSIDIAN = new HTMaterialKey("obsidian", 1014);
    public static final HTMaterialKey DIAMOND = new HTMaterialKey("diamond", 1015);
    public static final HTMaterialKey REDSTONE = new HTMaterialKey("redstone", 1016);
    public static final HTMaterialKey ICE = new HTMaterialKey("ice", 1017);
    public static final HTMaterialKey SNOW = new HTMaterialKey("snow", 1018);
    public static final HTMaterialKey CLAY = new HTMaterialKey("clay", 1019);
    public static final HTMaterialKey NETHERRACK = new HTMaterialKey("netherrack", 1020);
    public static final HTMaterialKey SOUL_SAND = new HTMaterialKey("soul_sand", 1021);
    public static final HTMaterialKey GLOWSTONE = new HTMaterialKey("glowstone", 1022);
    public static final HTMaterialKey NETHER_BRICK = new HTMaterialKey("nether_brick", 1023);
    public static final HTMaterialKey END_STONE = new HTMaterialKey("end_stone", 1024);
    public static final HTMaterialKey EMERALD = new HTMaterialKey("emerald", 1025);
    public static final HTMaterialKey QUARTZ = new HTMaterialKey("quartz", 1026);
    public static final HTMaterialKey SLIME = new HTMaterialKey("slime", 1027);
    public static final HTMaterialKey PRISMARINE = new HTMaterialKey("prismarine", 1028);
    public static final HTMaterialKey PURPUR = new HTMaterialKey("purpur", 1029);
    public static final HTMaterialKey CHARCOAL = new HTMaterialKey("charcoal", 1030);
    public static final HTMaterialKey GUNPOWDER = new HTMaterialKey("gunpowder", 1031);
    public static final HTMaterialKey FLINT = new HTMaterialKey("flint", 1032);
    public static final HTMaterialKey BONE = new HTMaterialKey("bone", 1033);
    public static final HTMaterialKey SUGAR = new HTMaterialKey("sugar", 1034);
    public static final HTMaterialKey ENDER_PEARL = new HTMaterialKey("ender_pearl", 1035);
    public static final HTMaterialKey BLAZE = new HTMaterialKey("blaze", 1036);
    public static final HTMaterialKey NETHER_STAR = new HTMaterialKey("nether_star", 1037);

    // Register //

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerMaterialKey(HTMaterialEvent.Register event) {
        for (Field field : HTVanillaMaterials.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(HTVanillaMaterials.INSTANCE);
                if (obj instanceof HTMaterialKey key) {
                    event.registry.add(key);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyProperty(HTMaterialEvent.Property event) {
        var registry = event.registry;
        registry.getOrCreate(STONE)
                .add(new HTCompoundProperty(HTAtomicGroup.SILICON_OXIDE));
        registry.getOrCreate(GRANITE)
                .add(new HTCompoundProperty(HTAtomicGroup.SILICON_OXIDE));
        registry.getOrCreate(DIORITE)
                .add(new HTCompoundProperty(HTAtomicGroup.SILICON_OXIDE));
        registry.getOrCreate(ANDESITE)
                .add(new HTCompoundProperty(HTAtomicGroup.SILICON_OXIDE));
        registry.getOrCreate(WOOD)
                .add(new HTMixtureProperty(HTElementMaterials.CARBON, HTElementMaterials.HYDROGEN,
                        HTElementMaterials.OXYGEN));
        registry.getOrCreate(BEDROCK)
                .add(new HTCompoundProperty(HTAtomicGroup.SILICON_OXIDE));
        registry.getOrCreate(WATER)
                .add(new HTCompoundProperty(builder -> {
                    builder.put(HTElementMaterials.HYDROGEN, 2);
                    builder.put(HTElementMaterials.OXYGEN, 1);
                }))
                .add(new HTFluidProperty());
        registry.getOrCreate(LAVA)
                .add(new HTCompoundProperty(HTAtomicGroup.SILICON_OXIDE))
                .add(new HTFluidProperty());
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyFlag(HTMaterialEvent.Flag event) {
        var registry = event.registry;
        registry.getOrCreate(STONE)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(GRANITE)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(DIORITE)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(ANDESITE)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(WOOD)
                .add(HTMaterialFlags.GENERATE_DUST)
                .add(HTMaterialFlags.GENERATE_GEAR)
                .add(HTMaterialFlags.GENERATE_PLATE)
                .add(HTMaterialFlags.GENERATE_STICK);
        registry.getOrCreate(BEDROCK)
                .add(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(WATER)
                .add(HTMaterialFlags.NOT_GENERATE_FLUID);
        registry.getOrCreate(LAVA)
                .add(HTMaterialFlags.NOT_GENERATE_FLUID);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyColor(HTMaterialEvent.Color event) {
        event.put(STONE, () -> HTColor.DARK_GRAY);
        event.put(GRANITE, ColorConvertible.ofColor(builder -> {
            builder.put(HTColor.DARK_RED, 1);
            builder.put(HTColor.GRAY, 4);
            builder.put(HTColor.RED, 1);
        }));
        event.put(DIORITE, () -> HTColor.GRAY);
        event.put(ANDESITE, ColorConvertible.ofColor(builder -> {
            builder.put(HTColor.DARK_GRAY, 7);
            builder.put(HTColor.YELLOW, 1);
        }));
        event.put(WOOD, ColorConvertible.ofColor(builder -> {
            builder.put(HTColor.DARK_GRAY, 2);
            builder.put(HTColor.RED, 1);
            builder.put(HTColor.YELLOW, 1);
        }));
        event.put(WATER, () -> HTColor.BLUE);
        event.put(LAVA, ColorConvertible.ofColor(HTColor.DARK_RED, HTColor.GOLD));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyFormula(HTMaterialEvent.Formula event) {}

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyMolar(HTMaterialEvent.Molar event) {}
}
