package io.github.hiiragi283.material.api.material;

import java.awt.*;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record HTMaterialInfo(Color color, String formula, double molar) {}
