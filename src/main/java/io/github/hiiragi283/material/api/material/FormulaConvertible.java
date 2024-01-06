package io.github.hiiragi283.material.api.material;

import com.google.common.collect.ImmutableMap;
import crafttweaker.annotations.ZenRegister;
import io.github.hiiragi283.material.compat.crt.HTCrTPlugin;
import io.github.hiiragi283.material.util.HTCollectors;
import org.jetbrains.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@FunctionalInterface
@ZenClass(HTCrTPlugin.MATERIAL_PREFIX + "FormulaConvertible")
@ZenRegister
public interface FormulaConvertible {

    @NotNull
    String asFormula();

    FormulaConvertible EMPTY = () -> "";

    static FormulaConvertible of(FormulaConvertible... formulas) {
        return () -> format(Arrays.stream(formulas).collect(HTCollectors.associate(FormulaConvertible::asFormula, formula -> 1)));
    }

    static FormulaConvertible of(Map<FormulaConvertible, Integer> map) {
        return () -> format(map.entrySet().stream().collect(HTCollectors.mapKeys(FormulaConvertible::asFormula)));
    }

    static FormulaConvertible ofString(String... formulas) {
        return () -> format(Arrays.stream(formulas).collect(HTCollectors.associateWith(1)));
    }

    static FormulaConvertible ofString(Consumer<ImmutableMap.Builder<String, Integer>> consumer) {
        var builder = new ImmutableMap.Builder<String, Integer>();
        consumer.accept(builder);
        return () -> format(builder.build());
    }

    static String format(Iterable<String> formulas) {
        return format(StreamSupport.stream(formulas.spliterator(), false));
    }

    static String format(Stream<String> formulas) {
        return format(formulas.collect(HTCollectors.associateWith(1)));
    }

    static String format(Map<String, Integer> map) {
        var builder = new StringBuilder();
        map.forEach((formula, weight) -> {
            builder.append(formula);
            if (weight > 1) {
                int weight1 = weight % 10;
                int weight10 = weight / 10;
                char char1 = (char) ('₀' + weight1);
                char char10 = (char) ('₀' + weight10);
                var builderInternal = new StringBuilder();
                if (weight10 > 0) {
                    builderInternal.append(char10);
                }
                builderInternal.append(char1);
                builder.append(builderInternal);
            }
        });
        return builder.toString();
    }

}