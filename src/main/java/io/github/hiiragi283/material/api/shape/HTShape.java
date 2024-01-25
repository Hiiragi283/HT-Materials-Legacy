package io.github.hiiragi283.material.api.shape;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.github.bsideup.jabel.Desugar;
import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.material.api.material.HTMaterial;

@Desugar
public record HTShape(HTShapeKey key, HTShapePredicate predicate) implements Predicate<HTMaterial> {

    @Override
    public boolean test(HTMaterial material) {
        return predicate.test(material);
    }

    // Registry //

    private static final Logger LOGGER = LogManager.getLogger("HTShape");

    private static final Map<String, HTShape> registry = new LinkedHashMap<>();

    public static Map<String, HTShape> getRegistry() {
        return ImmutableMap.copyOf(registry);
    }

    @NotNull
    public static Stream<HTShape> getShapes() {
        return getRegistry().values().stream();
    }

    @NotNull
    public static Stream<HTShapeKey> getShapeKeys() {
        return getShapes().map(HTShape::key);
    }

    @NotNull
    public static HTShape getShape(String name) {
        HTShape shape = getShapeOrNull(name);
        if (shape == null) {
            throw new IllegalStateException("Shape: " + name + " is not registered!");
        }
        return shape;
    }

    @Nullable
    public static HTShape getShapeOrNull(String name) {
        return registry.get(name);
    }

    static void create(
                       HTShapeKey key,
                       HTShapePredicate predicate) {
        var shape = new HTShape(key, predicate);
        registry.putIfAbsent(key.name(), shape);
        LOGGER.info("Shape: " + key + " registered!");
    }
}
