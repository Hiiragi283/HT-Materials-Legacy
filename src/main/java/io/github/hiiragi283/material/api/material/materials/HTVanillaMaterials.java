package io.github.hiiragi283.material.api.material.materials;

import io.github.hiiragi283.material.api.HTMaterialsAddon;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.property.HTCompoundProperty;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;

import java.lang.reflect.Field;

public enum HTVanillaMaterials implements HTMaterialsAddon {
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

    //    HTMaterialsAddon    //

    @Override
    public int getPriority() {
        return -90;
    }

    @Override
    public void registerMaterialKey(HTObjectKeySet<HTMaterialKey> registry) {
        for (Field field : HTVanillaMaterials.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(this);
                if (obj instanceof HTMaterialKey key) {
                    registry.add(key);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void modifyMaterialProperty(HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> registry) {
        registry.getOrCreate(WATER)
                .add(new HTCompoundProperty.Builder()
                        .put(HTElementMaterials.HYDROGEN, 2)
                        .put(HTElementMaterials.OXYGEN, 1)
                        .build()
                );
    }


}