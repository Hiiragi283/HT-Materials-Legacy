package io.github.hiiragi283.material.api.shape;

import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public abstract class HTShapeEvent extends Event {

    public static final class Register extends HTShapeEvent {

        @NotNull
        public final HTObjectKeySet<HTShapeKey> registry;

        private Register(@NotNull HTObjectKeySet<HTShapeKey> registry) {
            this.registry = registry;
        }

    }

    public static final class Predicate extends HTShapeEvent {

        @NotNull
        public final HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> registry;

        private Predicate(@NotNull HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> registry) {
            this.registry = registry;
        }

    }

    //    Init    //

    private static final HTObjectKeySet<HTShapeKey> shapeKeys = HTObjectKeySet.create();
    private static final HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> predicateMap = HTNonNullMap.create(key -> new HTShapePredicate.Builder());

    public static void init() {
        MinecraftForge.EVENT_BUS.post(new Register(shapeKeys));
        MinecraftForge.EVENT_BUS.post(new Predicate(predicateMap));
        createShapes();
    }

    private static void createShapes() {
        shapeKeys.stream().sorted(Comparator.comparing(HTShapeKey::name)).forEach(key -> {
            var predicate = predicateMap.getOrCreate(key).build();
            HTShape.create(key, predicate);
        });
    }

}