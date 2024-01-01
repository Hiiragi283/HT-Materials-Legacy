package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import org.jetbrains.annotations.NotNull;
import scala.actors.threadpool.Arrays;

import java.awt.*;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class HTMixtureProperty implements HTComponentProperty<HTMixtureProperty>, Iterable<HTMaterialKey> {

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
        return ColorConvertible.average(StreamSupport.stream(iterable.spliterator(), false).map(key -> key.getMaterial().getInfo().color()));
    }

    @Override
    public @NotNull String asFormula() {
        return "";
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