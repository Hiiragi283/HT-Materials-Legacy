package io.github.hiiragi283.material.api.shape;

import java.lang.reflect.Field;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import io.github.hiiragi283.material.HTMaterialsMod;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlags;
import io.github.hiiragi283.material.api.material.property.HTGemProperty;
import io.github.hiiragi283.material.api.material.property.HTPropertyKeys;

@Mod.EventBusSubscriber
public enum HTShapes {

    INSTANCE;

    // Block //

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

    // Item //

    public static final HTShapeKey DUST = new HTShapeKey("dust");
    public static final HTShapeKey GEAR = new HTShapeKey("gear");
    public static final HTShapeKey GEM = new HTShapeKey("gem");
    public static final HTShapeKey INGOT = new HTShapeKey("ingot");
    public static final HTShapeKey NUGGET = new HTShapeKey("nugget");
    public static final HTShapeKey PLATE = new HTShapeKey("plate");
    public static final HTShapeKey STICK = new HTShapeKey("stick");

    // Register //

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
        registry.put(DUST, material -> material.hasFlag(HTMaterialFlags.GENERATE_DUST));
        registry.put(GEM, material -> material.hasFlag(HTMaterialFlags.GENERATE_GEM));
        registry.put(GEAR, material -> material.hasFlag(HTMaterialFlags.GENERATE_GEAR));
        registry.put(INGOT, material -> material.hasFlag(HTMaterialFlags.GENERATE_INGOT));
        registry.put(NUGGET, material -> material.hasFlag(HTMaterialFlags.GENERATE_NUGGET));
        registry.put(PLATE, material -> material.hasFlag(HTMaterialFlags.GENERATE_PLATE));
        registry.put(STICK, material -> material.hasFlag(HTMaterialFlags.GENERATE_STICK));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void modifyModelFunctions(HTShapeEvent.Model event) {
        var registry = event.registry;
        registry.put(GEM, material -> {
            HTGemProperty gemProperty = material.getProperty(HTPropertyKeys.GEM);
            String path = gemProperty != null ? "gem_" + gemProperty.name() : GEM.name();
            return HTMaterialsMod.getId("gem_cubic");
        });
    }
}
