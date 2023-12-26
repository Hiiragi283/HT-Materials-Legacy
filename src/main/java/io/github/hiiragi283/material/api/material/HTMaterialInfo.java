package io.github.hiiragi283.material.api.material;

import com.github.bsideup.jabel.Desugar;

import java.awt.*;

@Desugar
public record HTMaterialInfo(Color color, String formula, double molar) {
}