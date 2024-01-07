package io.github.hiiragi283.material.api.shape;

import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber
public enum HTShapes {
    INSTANCE;

    //    Block    //

    public static final HTShapeKey BLOCK = new HTShapeKey("block");
    public static final HTShapeKey BRICK = new HTShapeKey("brick");
    public static final HTShapeKey FENCE = new HTShapeKey("fence");
    public static final HTShapeKey FENCE_GATE = new HTShapeKey("fence_gate");
    public static final HTShapeKey LOG = new HTShapeKey("log");
    public static final HTShapeKey ORE = new HTShapeKey("ore");
    public static final HTShapeKey PLANK = new HTShapeKey("plank");
    public static final HTShapeKey SAND = new HTShapeKey("sand");
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

    //    Register    //

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerShapeKey(HTShapeEvent.Register event) {
        for (Field field : HTShapes.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object obj = field.get(HTShapes.INSTANCE);
                if (obj instanceof HTShapeKey key) {
                    event.registry.add(key);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyPredicate(HTShapeEvent.Predicate event) {
        var registry = event.registry;
        registry.getOrCreate(DUST)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_DUST);
        registry.getOrCreate(GEAR)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_GEAR);
        registry.getOrCreate(INGOT)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_INGOT);
        registry.getOrCreate(NUGGET)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_NUGGET);
        registry.getOrCreate(PLATE)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_PLATE);
        registry.getOrCreate(STICK)
                .setEnabled()
                .addRequiredFlag(HTMaterialFlags.GENERATE_STICK);
    }

}