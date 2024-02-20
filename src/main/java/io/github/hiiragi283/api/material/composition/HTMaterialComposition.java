package io.github.hiiragi283.api.material.composition;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import io.github.hiiragi283.api.material.element.HTElement;

public record HTMaterialComposition(ImmutableMap<HTElement, Integer> component,
                                    Color color,
                                    String formula,
                                    double molar) {

    public HTMaterialComposition(Map<HTElement, Integer> component, Color color, String formula, double molar) {
        this(ImmutableMap.copyOf(component), color, formula, molar);
    }

    public static HTMaterialComposition EMPTY = new HTMaterialComposition(new HashMap<>(), Color.WHITE, "", 0.0);
}
