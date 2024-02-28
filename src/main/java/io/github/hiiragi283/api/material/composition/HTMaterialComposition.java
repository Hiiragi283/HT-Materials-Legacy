package io.github.hiiragi283.api.material.composition;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import com.github.bsideup.jabel.Desugar;
import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.api.material.element.HTElement;

@Desugar
public record HTMaterialComposition(@NotNull ImmutableMap<HTElement, Integer> component,
                                    @NotNull Color color,
                                    @NotNull String formula,
                                    double molar) {

    public HTMaterialComposition(@NotNull Map<HTElement, Integer> component, @NotNull Color color,
                                 @NotNull String formula, double molar) {
        this(ImmutableMap.copyOf(component), color, formula, molar);
    }

    public static HTMaterialComposition EMPTY = new HTMaterialComposition(new HashMap<>(), Color.WHITE, "", 0.0);
}
