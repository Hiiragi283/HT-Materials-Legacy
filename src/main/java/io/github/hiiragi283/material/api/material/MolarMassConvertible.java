package io.github.hiiragi283.material.api.material;

@FunctionalInterface
public interface MolarMassConvertible {

    MolarMassConvertible EMPTY = () -> 0.0;

    double asMolar();

}