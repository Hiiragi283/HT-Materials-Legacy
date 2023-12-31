package io.github.hiiragi283.material.api.material.property;

import com.google.common.collect.ImmutableMap;
import io.github.hiiragi283.material.api.HTCollectors;
import io.github.hiiragi283.material.api.material.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public class HTCompoundProperty implements HTComponentProperty<HTCompoundProperty> {

    private final Map<HTMaterialKey, Integer> backingMap;

    public HTCompoundProperty(Map<HTMaterialKey, Integer> map) {
        this.backingMap = map;
    }

    @NotNull
    public Map<HTMaterialKey, Integer> getMap() {
        return ImmutableMap.copyOf(backingMap);
    }

    @NotNull
    @Override
    public Color asColor() {
        return ColorConvertible.average(backingMap.entrySet().stream().collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).getInfo().color())));
    }

    @Override
    public String asFormula() {
        return FormulaConvertible.format(backingMap.entrySet().stream().collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).getInfo().formula())));
    }

    @Override
    public double asMolar() {
        return MolarMassConvertible.calculate(backingMap.entrySet().stream().collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).getInfo().molar())));
    }

    @NotNull
    @Override
    public HTPropertyKey<HTCompoundProperty> getKey() {
        return HTPropertyKeys.COMPOUND;
    }

}