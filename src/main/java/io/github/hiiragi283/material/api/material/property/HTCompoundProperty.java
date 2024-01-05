package io.github.hiiragi283.material.api.material.property;

import com.google.common.collect.ImmutableMap;
import io.github.hiiragi283.material.api.material.*;
import io.github.hiiragi283.material.util.HTCollectors;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;

public final class HTCompoundProperty implements HTComponentProperty<HTCompoundProperty> {

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
        return ColorConvertible.average(backingMap.entrySet().stream().collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).color())));
    }

    @NotNull
    @Override
    public String asFormula() {
        return FormulaConvertible.format(backingMap.entrySet().stream().collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).formula())));
    }

    @Override
    public double asMolar() {
        return MolarMassConvertible.calculate(backingMap.entrySet().stream().collect(HTCollectors.mapKeys(key -> HTMaterial.getMaterial(key.name()).molar())));
    }

    @NotNull
    @Override
    public HTPropertyKey<HTCompoundProperty> getKey() {
        return HTPropertyKeys.COMPOUND;
    }

    //    Builder    //

    public static final class Builder {

        private final ImmutableMap.Builder<HTMaterialKey, Integer> builder = ImmutableMap.builder();

        public Builder put(HTMaterialKey materialKey, int weight) {
            builder.put(materialKey, weight);
            return this;
        }

        public HTCompoundProperty build() {
            return new HTCompoundProperty(builder.build());
        }

    }

}