package io.github.hiiragi283.material.api.material;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.material.util.HTCollectors;

@FunctionalInterface
public interface MolarMassConvertible {

    double asMolar();

    MolarMassConvertible EMPTY = () -> 0.0;

    static MolarMassConvertible of(MolarMassConvertible... molars) {
        return () -> calculate(
                Arrays.stream(molars).collect(HTCollectors.associate(MolarMassConvertible::asMolar, color -> 1)));
    }

    static MolarMassConvertible of(Map<MolarMassConvertible, Integer> map) {
        return () -> calculate(map.entrySet().stream().collect(HTCollectors.mapKeys(MolarMassConvertible::asMolar)));
    }

    static MolarMassConvertible ofDouble(Double... molars) {
        return () -> calculate(Arrays.stream(molars).collect(HTCollectors.associateWith(1)));
    }

    static MolarMassConvertible ofDouble(Consumer<ImmutableMap.Builder<Double, Integer>> consumer) {
        var builder = new ImmutableMap.Builder<Double, Integer>();
        consumer.accept(builder);
        return () -> calculate(builder.build());
    }

    static double calculate(Iterable<Double> molars) {
        return calculate(StreamSupport.stream(molars.spliterator(), false));
    }

    static double calculate(Stream<Double> molars) {
        return calculate(molars.collect(HTCollectors.associateWith(1)));
    }

    static double calculate(Map<Double, Integer> map) {
        return map.entrySet().stream().map(entry -> entry.getKey() * entry.getValue()).mapToDouble(Double::doubleValue)
                .sum();
    }
}
