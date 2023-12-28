package io.github.hiiragi283.material.api.shape;

import io.github.hiiragi283.material.HMCommonProxy;
import io.github.hiiragi283.material.HMReference;
import io.github.hiiragi283.material.api.HTAddon;
import io.github.hiiragi283.material.api.HTMaterialsAddon;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;

@HTAddon
public class HTShapes implements HTMaterialsAddon {

    //    Block    //

    public static HTShapeKey BLOCK = new HTShapeKey("block");

    public static HTShapeKey ORE = new HTShapeKey("ore");

    //    Item    //

    public static HTShapeKey DUST = new HTShapeKey("dust");

    public static HTShapeKey GEAR = new HTShapeKey("gear");

    public static HTShapeKey GEM = new HTShapeKey("gem");

    public static HTShapeKey INGOT = new HTShapeKey("ingot");

    public static HTShapeKey NUGGET = new HTShapeKey("nugget");

    public static HTShapeKey PLATE = new HTShapeKey("plate");

    public static HTShapeKey STICK = new HTShapeKey("stick");

    //    HTMaterialsAddon    //

    @Override
    public String getModId() {
        return HMReference.MOD_ID;
    }

    @Override
    public int getPriority() {
        return -120;
    }

    @Override
    public void registerShapeKey(HTObjectKeySet<HTShapeKey> registry) {
        //Block
        registry.addAll(
                BLOCK,
                ORE
        );
        //Item
        registry.addAll(
                DUST,
                GEAR,
                GEM,
                INGOT,
                NUGGET,
                PLATE,
                STICK
        );
    }

    @Override
    public void modifyShapePredicate(HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> registry) {
        //registry.getOrCreate(BLOCK).disabled = false;
        //registry.getOrCreate(ORE).disabled = false;
        registry.getOrCreate(DUST).disabled = false;
        registry.getOrCreate(GEAR).disabled = false;
        //registry.getOrCreate(GEM).disabled = false;
        registry.getOrCreate(INGOT).disabled = false;
        registry.getOrCreate(NUGGET).disabled = false;
        registry.getOrCreate(PLATE).disabled = false;
        registry.getOrCreate(STICK).disabled = false;
    }

    //    Init    //

    public static void init() throws IllegalAccessException {
        registerShapeKey();
        modifyShapePredicate();
        createShape();
    }

    private static final HTObjectKeySet<HTShapeKey> shapeKeySet = HTObjectKeySet.create();

    private static void registerShapeKey() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.registerShapeKey(shapeKeySet));
    }

    private static final HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> predicateMap = HTNonNullMap.create(key -> new HTShapePredicate.Builder());

    private static void modifyShapePredicate() throws IllegalAccessException {
        HMCommonProxy.getAddons().forEach(addon -> addon.modifyShapePredicate(predicateMap));
    }

    private static void createShape() {
        shapeKeySet.forEach(key -> {
            var predicate = predicateMap.getOrCreate(key).build();
            HTShape.create(key, predicate);
        });
    }

}