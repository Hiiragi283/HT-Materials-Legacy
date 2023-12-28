package io.github.hiiragi283.material.api.material;

@FunctionalInterface
public interface FormulaConvertible {

    FormulaConvertible EMPTY = () -> "";

    String asFormula();

}