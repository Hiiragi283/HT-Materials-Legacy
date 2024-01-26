package io.github.hiiragi283.material.api.shape;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Predicates;

import io.github.hiiragi283.material.HTMaterialsMod;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;

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
        public final Map<HTShapeKey, java.util.function.Predicate<HTMaterial>> registry;

        private Predicate(@NotNull Map<HTShapeKey, java.util.function.Predicate<HTMaterial>> registry) {
            this.registry = registry;
        }
    }

    public static final class Model extends HTShapeEvent {

        @NotNull
        public final Map<HTShapeKey, Function<HTMaterial, ResourceLocation>> registry;

        private Model(@NotNull Map<HTShapeKey, Function<HTMaterial, ResourceLocation>> registry) {
            this.registry = registry;
        }
    }

    // Init //

    private static final HTObjectKeySet<HTShapeKey> shapeKeys = HTObjectKeySet.create();
    private static final Map<HTShapeKey, java.util.function.Predicate<HTMaterial>> predicateMap = new HashMap<>();
    private static final Map<HTShapeKey, Function<HTMaterial, ResourceLocation>> modelMap = new HashMap<>();

    public static void init() {
        MinecraftForge.EVENT_BUS.post(new Register(shapeKeys));
        MinecraftForge.EVENT_BUS.post(new Predicate(predicateMap));
        MinecraftForge.EVENT_BUS.post(new Model(modelMap));
        createShapes();
    }

    private static void createShapes() {
        shapeKeys.stream().sorted(Comparator.comparing(HTShapeKey::name)).forEach(key -> {
            var predicate = predicateMap.getOrDefault(key, Predicates.alwaysFalse());
            var model = modelMap.getOrDefault(key,
                    material -> new ModelResourceLocation(HTMaterialsMod.getId(key.name()), "inventory"));
            HTShape.create(key, predicate, model);
        });
    }
}
