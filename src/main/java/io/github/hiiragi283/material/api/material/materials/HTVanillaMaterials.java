package io.github.hiiragi283.material.api.material.materials;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialEvent;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.material.property.component.HTCompoundProperty;
import io.github.hiiragi283.material.api.material.property.component.HTMixtureProperty;
import io.github.hiiragi283.material.util.HTColor;

@Mod.EventBusSubscriber
public enum HTVanillaMaterials {

    INSTANCE;

    public static final HTMaterialKey STONE = new HTMaterialKey("stone");
    public static final HTMaterialKey GRANITE = new HTMaterialKey("granite");
    public static final HTMaterialKey DIORITE = new HTMaterialKey("diorite");
    public static final HTMaterialKey ANDESITE = new HTMaterialKey("andesite");
    public static final HTMaterialKey WOOD = new HTMaterialKey("wood");
    public static final HTMaterialKey BEDROCK = new HTMaterialKey("bedrock");
    public static final HTMaterialKey WATER = new HTMaterialKey("water");
    public static final HTMaterialKey LAVA = new HTMaterialKey("lava");
    public static final HTMaterialKey SAND = new HTMaterialKey("sand");
    public static final HTMaterialKey COAL = new HTMaterialKey("coal");
    public static final HTMaterialKey SPONGE = new HTMaterialKey("sponge");
    public static final HTMaterialKey GLASS = new HTMaterialKey("glass");
    public static final HTMaterialKey LAPIS = new HTMaterialKey("lapis");
    public static final HTMaterialKey BRICK = new HTMaterialKey("brick");
    public static final HTMaterialKey OBSIDIAN = new HTMaterialKey("obsidian");
    public static final HTMaterialKey DIAMOND = new HTMaterialKey("diamond");
    public static final HTMaterialKey REDSTONE = new HTMaterialKey("redstone");
    public static final HTMaterialKey ICE = new HTMaterialKey("ice");
    public static final HTMaterialKey SNOW = new HTMaterialKey("snow");
    public static final HTMaterialKey CLAY = new HTMaterialKey("clay");
    public static final HTMaterialKey NETHERRACK = new HTMaterialKey("netherrack");
    public static final HTMaterialKey SOUL_SAND = new HTMaterialKey("soul_sand");
    public static final HTMaterialKey GLOWSTONE = new HTMaterialKey("glowstone");
    public static final HTMaterialKey NETHER_BRICK = new HTMaterialKey("nether_brick");
    public static final HTMaterialKey END_STONE = new HTMaterialKey("end_stone");
    public static final HTMaterialKey EMERALD = new HTMaterialKey("emerald");
    public static final HTMaterialKey QUARTZ = new HTMaterialKey("quartz");
    public static final HTMaterialKey SLIME = new HTMaterialKey("slime");
    public static final HTMaterialKey PRISMARINE = new HTMaterialKey("prismarine");
    public static final HTMaterialKey PURPUR = new HTMaterialKey("purpur");
    public static final HTMaterialKey CHARCOAL = new HTMaterialKey("charcoal");
    public static final HTMaterialKey GUNPOWDER = new HTMaterialKey("gunpowder");
    public static final HTMaterialKey FLINT = new HTMaterialKey("flint");
    public static final HTMaterialKey BONE = new HTMaterialKey("bone");
    public static final HTMaterialKey SUGAR = new HTMaterialKey("sugar");
    public static final HTMaterialKey ENDER_PEARL = new HTMaterialKey("ender_pearl");
    public static final HTMaterialKey BLAZE = new HTMaterialKey("blaze");
    public static final HTMaterialKey NETHER_STAR = new HTMaterialKey("nether_star");

    // Register //

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerMaterialKey(HTMaterialEvent.Register event) {
        var registry = event.registry;
        registry.put(STONE, 1000);
        registry.put(GRANITE, 1001);
        registry.put(DIORITE, 1002);
        registry.put(ANDESITE, 1003);
        registry.put(WOOD, 1004);
        registry.put(BEDROCK, 1005);
        registry.put(WATER, 1006);
        registry.put(LAVA, 1007);
        registry.put(SAND, 1008);
        registry.put(COAL, 1009);
        registry.put(SPONGE, 1010);
        registry.put(GLASS, 1011);
        registry.put(LAPIS, 1012);
        registry.put(BRICK, 1013);
        registry.put(OBSIDIAN, 1014);
        registry.put(DIAMOND, 1015);
        registry.put(REDSTONE, 10106);
        registry.put(ICE, 1017);
        registry.put(SNOW, 1018);
        registry.put(CLAY, 1019);
        registry.put(NETHERRACK, 1020);
        registry.put(SOUL_SAND, 1021);
        registry.put(GLOWSTONE, 1022);
        registry.put(NETHER_BRICK, 1023);
        registry.put(END_STONE, 1024);
        registry.put(EMERALD, 1025);
        registry.put(QUARTZ, 1026);
        registry.put(SLIME, 1027);
        registry.put(PRISMARINE, 1028);
        registry.put(PURPUR, 1029);
        registry.put(CHARCOAL, 1030);
        registry.put(GUNPOWDER, 1031);
        registry.put(FLINT, 1032);
        registry.put(BONE, 1033);
        registry.put(SUGAR, 1034);
        registry.put(ENDER_PEARL, 1035);
        registry.put(BLAZE, 1036);
        registry.put(NETHER_STAR, 1037);
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
                }));
        registry.getOrCreate(LAVA)
                .add(new HTCompoundProperty(HTAtomicGroup.SILICON_OXIDE));
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
        registry.getOrCreate(WATER);
        registry.getOrCreate(LAVA);
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
