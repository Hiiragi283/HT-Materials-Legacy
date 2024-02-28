package io.github.hiiragi283.api.material.element;

import java.awt.*;
import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record HTElement(@NotNull Color color, @NotNull String formula, double molar) {

    @NotNull
    public static HTElement build(@NotNull Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return builder.build();
    }

    @NotNull
    public HTElement bracket() {
        return new HTElement(color, "(" + formula + ")", molar);
    }

    public static class Builder {

        @NotNull
        public Color color = Color.WHITE;
        @NotNull
        public String formula = "";
        public double molar = 0.0;

        HTElement build() {
            return new HTElement(color, formula, molar);
        }
    }
}
