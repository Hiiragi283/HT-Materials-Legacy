package io.github.hiiragi283.htms.api.material.composition;

import java.awt.*;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import com.github.bsideup.jabel.Desugar;

import io.github.hiiragi283.htms.api.extension.HTColor;
import io.github.hiiragi283.htms.api.extension.HTColorUtil;
import io.github.hiiragi283.htms.api.extension.HTFormulaUtil;
import io.github.hiiragi283.htms.api.extension.HTMolarUtil;

@Desugar
public record HTElement(@NotNull Color color, @NotNull String formula, double molar) {

    public static @NotNull HTElement group(@NotNull Map<HTElement, Integer> elements,
                                           @NotNull Consumer<HTElement.Builder> consumer) {
        HTElement.Builder builder = new Builder(elements);
        consumer.accept(builder);
        return builder.build();
    }

    public @NotNull HTElement bracket() {
        return new HTElement(color, "(" + formula + ")", 0.0);
    }

    public static class Builder {

        @NotNull
        public Color color = HTColor.WHITE;
        @NotNull
        public String formula = "";
        public double molar = 0.0;

        private Builder(Map<HTElement, Integer> elements) {
            color = HTColorUtil.averageColor(elements, HTElement::color);
            formula = HTFormulaUtil.formatFormula(elements, HTElement::formula);
            molar = HTMolarUtil.calculateMolar(elements, HTElement::molar);
        }

        @NotNull
        HTElement build() {
            return new HTElement(color, formula, molar);
        }
    }
}
