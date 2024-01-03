package io.github.hiiragi283.material.api.shape;

import io.github.hiiragi283.material.api.HTMaterialsAddon;
import io.github.hiiragi283.material.api.HTMaterialsAddonManager;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;

import java.lang.reflect.Field;

public enum HTShapes implements HTMaterialsAddon {
    INSTANCE;

    //    Block    //

    public static final HTShapeKey BLOCK = new HTShapeKey("block");

    public static final HTShapeKey FENCE = new HTShapeKey("fence");

    public static final HTShapeKey FENCE_GATE = new HTShapeKey("fence_gate");

    public static final HTShapeKey LOG = new HTShapeKey("log");

    public static final HTShapeKey ORE = new HTShapeKey("ore");

    public static final HTShapeKey PLANK = new HTShapeKey("plank");

    public static final HTShapeKey SLAB = new HTShapeKey("slab");

    public static final HTShapeKey STAIR = new HTShapeKey("stair");

    public static final HTShapeKey STONE = new HTShapeKey("stone");

    //    Item    //

    public static final HTShapeKey DUST = new HTShapeKey("dust");

    public static final HTShapeKey GEAR = new HTShapeKey("gear");

    public static final HTShapeKey GEM = new HTShapeKey("gem");

    public static final HTShapeKey INGOT = new HTShapeKey("ingot");

    public static final HTShapeKey NUGGET = new HTShapeKey("nugget");

    public static final HTShapeKey PLATE = new HTShapeKey("plate");

    public static final HTShapeKey STICK = new HTShapeKey("stick");

    //    HTMaterialsAddon    //

    @Override
    public int getPriority() {
        return -120;
    }

    @Override
    public void registerShapeKey(HTObjectKeySet<HTShapeKey> registry) {
        for (Field field : HTShapes.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(this);
                if (obj instanceof HTShapeKey key) {
                    registry.add(key);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void modifyShapePredicate(HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> registry) {

        //registry.getOrCreate(BLOCK).disabled = false;

        //registry.getOrCreate(ORE).disabled = false;

        registry.getOrCreate(DUST)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_DUST);

        registry.getOrCreate(GEAR)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_GEAR);

        //registry.getOrCreate(GEM).disabled = false;

        registry.getOrCreate(INGOT)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_INGOT);

        registry.getOrCreate(NUGGET)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_NUGGET);

        registry.getOrCreate(PLATE)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_PLATE);

        registry.getOrCreate(STICK)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_STICK);

    }

    //    Init    //

    public static void init() throws IllegalAccessException {
        registerShapeKey();
        modifyShapePredicate();
        createShape();
    }

    private static final HTObjectKeySet<HTShapeKey> shapeKeySet = HTObjectKeySet.create();

    private static void registerShapeKey() throws IllegalAccessException {
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.registerShapeKey(shapeKeySet));
    }

    private static final HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> predicateMap = HTNonNullMap.create(key -> new HTShapePredicate.Builder());

    private static void modifyShapePredicate() throws IllegalAccessException {
        HTMaterialsAddonManager.getAddons().forEach(addon -> addon.modifyShapePredicate(predicateMap));
    }

    private static void createShape() {
        shapeKeySet.forEach(key -> {
            var predicate = predicateMap.getOrCreate(key).build();
            HTShape.create(key, predicate);
        });
    }

}