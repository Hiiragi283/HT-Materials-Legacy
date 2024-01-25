package io.github.hiiragi283.material.api.material.property;

import java.awt.*;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.jetbrains.annotations.NotNull;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.util.HTUtils;
import scala.actors.threadpool.Arrays;

public final class HTMixtureProperty implements HTComponentProperty<HTMixtureProperty>, Iterable<HTMaterialKey> {

    private final Iterable<HTMaterialKey> iterable;

    @SuppressWarnings("unchecked")
    public HTMixtureProperty(HTMaterialKey... keys) {
        this(Arrays.asList(keys));
    }

    public HTMixtureProperty(Iterable<HTMaterialKey> iterable) {
        this.iterable = iterable;
    }

    @NotNull
    @Override
    public Iterator<HTMaterialKey> iterator() {
        return iterable.iterator();
    }

    @NotNull
    @Override
    public Color asColor() {
        return ColorConvertible
                .average(StreamSupport.stream(iterable.spliterator(), false).map(key -> key.getMaterial().color()));
    }

    @Override
    public @NotNull String asFormula() {
        return HTUtils.joinToString(", ",
                StreamSupport.stream(iterable.spliterator(), false).map(key -> key.getMaterial().formula()));
    }

    @Override
    public double asMolar() {
        return 0.0;
    }

    @Override
    public @NotNull HTPropertyKey<HTMixtureProperty> getKey() {
        return HTPropertyKeys.MIXTURE;
    }
}
