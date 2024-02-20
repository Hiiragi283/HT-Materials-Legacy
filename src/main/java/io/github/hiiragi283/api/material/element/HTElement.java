package io.github.hiiragi283.api.material.element;

import java.awt.*;
import java.util.function.Consumer;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record HTElement(Color color, String formula, double molar) {

    public static HTElement build(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return builder.build();
    }

    public HTElement bracket() {
        return new HTElement(color, "(" + formula + ")", molar);
    }

    public static class Builder {

        public Color color = Color.WHITE;
        public String formula = "";
        public double molar = 0.0;

        HTElement build() {
            return new HTElement(color, formula, molar);
        }
    }
}
