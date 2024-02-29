package io.github.hiiragi283.api.shape;

import java.util.Collection;
import java.util.stream.Stream;

import com.github.bsideup.jabel.Desugar;

@Desugar
public record HTShape(HTShapeKey key, Collection<HTShapeItemBuilder> itemBuilders) {

    public Stream<HTShapeItemBuilder> ItemBuilderStream() {
        return itemBuilders.stream();
    }
}
