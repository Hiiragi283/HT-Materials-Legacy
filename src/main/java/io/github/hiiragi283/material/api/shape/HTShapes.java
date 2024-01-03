package io.github.hiiragi283.material.api.shape;

import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public enum HTShapes {
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

    //    Init    //

    private static HTObjectKeySet<HTShapeKey> shapeKeys;
    private static HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> predicateMap;

    public static void init() {
        shapeKeys = HTShapeEvent.register();
        predicateMap = HTShapeEvent.predicate();
        createShapes();
    }

    private static void createShapes() {
        shapeKeys.forEach(key -> {
            var predicate = predicateMap.getOrCreate(key).build();
            HTShape.create(key, predicate);
        });
    }

    //    Register    //

    @SubscribeEvent
    public static void registerShapeKey(HTShapeEvent.Register event) {
        for (Field field : HTShapes.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(HTShapes.INSTANCE);
                if (obj instanceof HTShapeKey key) {
                    event.add(key);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SubscribeEvent
    public static void modifyPredicate(HTShapeEvent.Predicate event) {
        //event.getOrCreate(BLOCK).disabled = false;
        //event.getOrCreate(ORE).disabled = false;
        event.getOrCreate(DUST)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_DUST);
        event.getOrCreate(GEAR)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_GEAR);
        //event.getOrCreate(GEM).disabled = false;
        event.getOrCreate(INGOT)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_INGOT);
        event.getOrCreate(NUGGET)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_NUGGET);
        event.getOrCreate(PLATE)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_PLATE);
        event.getOrCreate(STICK)
                .setDisabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_STICK);
    }

}