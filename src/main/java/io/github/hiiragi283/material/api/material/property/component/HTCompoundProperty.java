package io.github.hiiragi283.material.api.material.property.component;

import java.awt.*;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.material.api.material.*;
import io.github.hiiragi283.material.api.material.property.HTPropertyKey;
import io.github.hiiragi283.material.api.material.property.HTPropertyKeys;
import io.github.hiiragi283.material.util.HTCollectors;

public final class HTCompoundProperty extends HTComponentPropertyBase<HTCompoundProperty> {

    private final Map<HTMaterialKey, Integer> backingMap;

    public HTCompoundProperty(Map<HTMaterialKey, Integer> map) {
        this.backingMap = map;
    }

    public HTCompoundProperty(Consumer<ImmutableMap.Builder<HTMaterialKey, Integer>> consumer) {
        var builder = new ImmutableMap.Builder<HTMaterialKey, Integer>();
        consumer.accept(builder);
        this.backingMap = builder.build();
    }

    @NotNull
    public Map<HTMaterialKey, Integer> getMap() {
        return ImmutableMap.copyOf(backingMap);
    }

    @NotNull
    @Override
    public Color asColor() {
        return ColorConvertible.average(backingMap.entrySet().stream()
                .collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).color())));
    }

    @NotNull
    @Override
    public String asFormula() {
        return FormulaConvertible.format(backingMap.entrySet().stream()
                .collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).formula())));
    }

    @Override
    public double asMolar() {
        return MolarMassConvertible.calculate(backingMap.entrySet().stream()
                .collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).molar())));
    }

    @NotNull
    @Override
    public HTPropertyKey<HTCompoundProperty> getKey() {
        return HTPropertyKeys.COMPOUND;
    }
}
