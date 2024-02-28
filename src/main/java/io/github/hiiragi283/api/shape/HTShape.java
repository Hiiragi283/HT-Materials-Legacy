package io.github.hiiragi283.api.shape;

import java.util.Collection;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record HTShape(HTShapeKey key, Collection<HTShapeItemBuilder> itemBuilders) {}
