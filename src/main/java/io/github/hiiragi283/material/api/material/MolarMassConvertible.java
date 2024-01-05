package io.github.hiiragi283.material.api.material;

import crafttweaker.annotations.ZenRegister;
import io.github.hiiragi283.material.compat.crt.HTCrTPlugin;
import io.github.hiiragi283.material.util.HTCollectors;
import stanhebben.zenscript.annotations.ZenClass;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@FunctionalInterface
@ZenClass(HTCrTPlugin.MATERIAL_PREFIX + "MolarMassConvertible")
@ZenRegister
public interface MolarMassConvertible {

    double asMolar();

    MolarMassConvertible EMPTY = () -> 0.0;

    static MolarMassConvertible of(MolarMassConvertible... molars) {
        return ofDouble(Arrays.stream(molars).collect(HTCollectors.associate(MolarMassConvertible::asMolar, color -> 1)));
    }

    static MolarMassConvertible of(Map<MolarMassConvertible, Integer> map) {
        return ofDouble(map.entrySet().stream().collect(HTCollectors.mapKeys(MolarMassConvertible::asMolar)));
    }

    static MolarMassConvertible ofDouble(Double... molars) {
        return ofDouble(Arrays.stream(molars).collect(HTCollectors.associateWith(1)));
    }

    static MolarMassConvertible ofDouble(Map<Double, Integer> map) {
        return () -> calculate(map);
    }

    static double calculate(Iterable<Double> molars) {
        return calculate(StreamSupport.stream(molars.spliterator(), false));
    }

    static double calculate(Stream<Double> molars) {
        return calculate(molars.collect(HTCollectors.associateWith(1)));
    }

    static double calculate(Map<Double, Integer> map) {
        return map.entrySet().stream().map(entry -> entry.getKey() * entry.getValue()).mapToDouble(Double::doubleValue).sum();
    }

}