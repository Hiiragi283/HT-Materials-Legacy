package io.github.hiiragi283.htms.api.material.composition;

import java.awt.*;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.htms.api.extension.HTColor;
import io.github.hiiragi283.htms.api.extension.HTColorUtil;
import io.github.hiiragi283.htms.api.extension.HTFormulaUtil;
import io.github.hiiragi283.htms.api.extension.HTMolarUtil;

public interface HTMaterialComposition {

    @NotNull
    ImmutableMap<HTElement, Integer> componentMap();

    @NotNull
    Color color();

    @NotNull
    String formula();

    double molar();

    enum Empty implements HTMaterialComposition {

        INSTANCE;

        @Override
        public @NotNull ImmutableMap<HTElement, Integer> componentMap() {
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

    class Mutable implements HTMaterialComposition {

        private final Map<HTElement, Integer> componentMap;
        public Color color;
        public String formula;
        public double molar;

        private Mutable(Map<HTElement, Integer> componentMap) {
            this.componentMap = componentMap;
            color = HTColorUtil.averageColor(componentMap, HTElement::color);
            formula = HTFormulaUtil.formatFormula(componentMap, HTElement::formula);
            molar = HTMolarUtil.calculateMolar(componentMap, HTElement::molar);
        }

        @Override
        public @NotNull ImmutableMap<HTElement, Integer> componentMap() {
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
