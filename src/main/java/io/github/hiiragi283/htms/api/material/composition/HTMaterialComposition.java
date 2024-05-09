package io.github.hiiragi283.htms.api.material.composition;

import java.awt.*;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.extension.HTColor;
import io.github.hiiragi283.htms.api.extension.HTColorUtil;
import io.github.hiiragi283.htms.api.extension.HTFormulaUtil;
import io.github.hiiragi283.htms.api.extension.HTMolarUtil;

public interface HTMaterialComposition {

    @NotNull
    ImmutableMap<@NotNull HTElement, @NotNull Integer> componentMap();

    @NotNull
    Color color();

    @NotNull
    String formula();

    double molar();

    // Molecular //

    static @NotNull HTMaterialComposition molecular(@NotNull Consumer<ImmutableMap.@NotNull Builder<@NotNull HTElement, @NotNull Integer>> mapConsumer) {
        return molecular(mapConsumer, builder -> {});
    }

    static @NotNull HTMaterialComposition molecular(@NotNull Consumer<ImmutableMap.@NotNull Builder<@NotNull HTElement, @NotNull Integer>> mapConsumer,
                                                    @NotNull Consumer<@NotNull Builder> builderConsumer) {
        ImmutableMap.Builder<HTElement, Integer> mapBuilder = ImmutableMap.builder();
        mapConsumer.accept(mapBuilder);
        return molecular(mapBuilder.build(), builderConsumer);
    }

    static @NotNull HTMaterialComposition molecular(@NotNull Map<@NotNull HTElement, @NotNull Integer> map) {
        return molecular(map, builder -> {});
    }

    static @NotNull HTMaterialComposition molecular(@NotNull Map<@NotNull HTElement, @NotNull Integer> map,
                                                    @NotNull Consumer<@NotNull Builder> builderConsumer) {
        Builder builder = new Builder(map);
        builderConsumer.accept(builder);
        return builder;
    }

    // Hydrate //

    static @NotNull HTMaterialComposition hydrate(@NotNull HTMaterialComposition unhydrate, int waterCount) {
        return hydrate(unhydrate, waterCount, builder -> {});
    }

    static @NotNull HTMaterialComposition hydrate(@NotNull HTMaterialComposition unhydrate, int waterCount,
                                                  @NotNull Consumer<@NotNull Builder> builderConsumer) {
        ImmutableMap.Builder<HTElement, Integer> mapBuilder = ImmutableMap.builder();
        mapBuilder.putAll(unhydrate.componentMap());
        mapBuilder.put(HTElements.WATER, waterCount);
        Builder builder = new Builder(mapBuilder.build());
        builder.setColor(HTColor.WHITE);
        builder.setFormula(unhydrate.formula() + "-" + waterCount + "H₂O");
        builder.setMolar(unhydrate.molar() + waterCount * 18.0);
        builderConsumer.accept(builder);
        return builder;
    }

    // Mixture //

    static @NotNull HTMaterialComposition mixture(@NotNull HTElement... elements) {
        ImmutableMap.Builder<HTElement, Integer> mapBuilder = ImmutableMap.builder();
        for (HTElement element : elements) {
            mapBuilder.put(element, 1);
        }
        return new Builder(mapBuilder.build());
    }

    static @NotNull HTMaterialComposition mixture(@NotNull Collection<@NotNull HTElement> elements) {
        return mixture(elements, builder -> {});
    }

    static @NotNull HTMaterialComposition mixture(@NotNull Collection<@NotNull HTElement> elements,
                                                  @NotNull Consumer<@NotNull Builder> builderConsumer) {
        Builder builder = new Builder(elements.stream().collect(Collectors.toMap(Function.identity(), element -> 1)));
        builder.setColor(HTColorUtil.averageColor(elements, HTElement::color));
        builder.setFormula("");
        builder.setMolar(0.0);
        builderConsumer.accept(builder);
        return builder;
    }

    // Polymer //

    static @NotNull HTMaterialComposition polymer(@NotNull HTMaterialComposition monomar) {
        return polymer(monomar, builder -> {});
    }

    static @NotNull HTMaterialComposition polymer(@NotNull HTMaterialComposition monomar,
                                                  @NotNull Consumer<@NotNull Builder> builderConsumer) {
        Builder builder = new Builder(monomar.componentMap());
        builder.setColor(monomar.color());
        builder.setFormula("(" + monomar.formula() + ")n");
        builder.setMolar(0.0);
        builderConsumer.accept(builder);
        return builder;
    }

    // Empty //

    static HTMaterialComposition empty() {
        return new Empty();
    }

    class Empty implements HTMaterialComposition {

        private Empty() {}

        @Override
        public @NotNull ImmutableMap<@NotNull HTElement, @NotNull Integer> componentMap() {
            return ImmutableMap.of();
        }

        @Override
        public @NotNull Color color() {
            return HTColor.WHITE;
        }

        @Override
        public @NotNull String formula() {
            return "";
        }

        @Override
        public double molar() {
            return 0;
        }
    }

    // Builder //

    class Builder implements HTMaterialComposition {

        @NotNull
        private final Map<@NotNull HTElement, @NotNull Integer> componentMap;
        @NotNull
        private Color color;
        @NotNull
        private String formula;
        private double molar;

        private Builder(@NotNull Map<@NotNull HTElement, @NotNull Integer> componentMap) {
            this.componentMap = componentMap;
            color = HTColorUtil.averageColor(componentMap, HTElement::color);
            formula = HTFormulaUtil.formatFormula(componentMap, HTElement::formula);
            molar = HTMolarUtil.calculateMolar(componentMap, HTElement::molar);
        }

        public @NotNull HTMaterialComposition.Builder setColor(int color) {
            return setColor(new Color(color));
        }

        public @NotNull HTMaterialComposition.Builder setColor(@NotNull Color color) {
            this.color = color;
            return this;
        }

        public @NotNull HTMaterialComposition.Builder setFormula(@NotNull String formula) {
            this.formula = formula;
            return this;
        }

        public @NotNull HTMaterialComposition.Builder setMolar(double molar) {
            this.molar = molar;
            return this;
        }

        @Override
        public @NotNull ImmutableMap<@NotNull HTElement, @NotNull Integer> componentMap() {
            return ImmutableMap.copyOf(componentMap);
        }

        @Override
        public @NotNull Color color() {
            return color;
        }

        @Override
        public @NotNull String formula() {
            return formula;
        }

        @Override
        public double molar() {
            return molar;
        }
    }
}
