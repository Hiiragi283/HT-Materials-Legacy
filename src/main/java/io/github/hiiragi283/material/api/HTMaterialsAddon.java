package io.github.hiiragi283.material.api;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.FormulaConvertible;
import io.github.hiiragi283.material.api.material.HTMaterialKey;
import io.github.hiiragi283.material.api.material.MolarMassConvertible;
import io.github.hiiragi283.material.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.material.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.material.api.registry.HTNonNullMap;
import io.github.hiiragi283.material.api.registry.HTObjectKeySet;
import io.github.hiiragi283.material.api.shape.HTShapeKey;
import io.github.hiiragi283.material.api.shape.HTShapePredicate;

import java.util.Map;

public interface HTMaterialsAddon {

    String getModId();

    int getPriority();

    default void registerShapeKey(HTObjectKeySet<HTShapeKey> registry) {
    }

    default void modifyShapePredicate(HTNonNullMap<HTShapeKey, HTShapePredicate.Builder> registry) {
    }

    default void registerMaterialKey(HTObjectKeySet<HTMaterialKey> registry) {
    }

    default void modifyMaterialProperty(HTNonNullMap<HTMaterialKey, HTMaterialPropertyMap.Builder> registry) {
    }

    default void modifyMaterialFlag(HTNonNullMap<HTMaterialKey, HTMaterialFlagSet.Builder> registry) {
    }

    default void modifyMaterialColor(Map<HTMaterialKey, ColorConvertible> registry) {
    }

    default void modifyMaterialFormula(Map<HTMaterialKey, FormulaConvertible> registry) {
    }

    default void modifyMaterialMolar(Map<HTMaterialKey, MolarMassConvertible> registry) {
    }

}