package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.FormulaConvertible;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.MolarMassConvertible;

public interface IComponentProperty<T extends IComponentProperty<T>> extends HTMaterialProperty<T>, ColorConvertible,
                                   FormulaConvertible, MolarMassConvertible {

    @Override
    default void verify(HTMaterial material) {
        material.getProperties().values().forEach(prop -> {
            if (prop.getKey() != getKey() && prop instanceof ColorConvertible) {
                throw new IllegalStateException(
                        "Material: ${material.key} cannot have two or more properties implemented ColorConvertible!");
            }
            if (prop.getKey() != getKey() && prop instanceof FormulaConvertible) {
                throw new IllegalStateException(
                        "Material: ${material.key} cannot have two or more properties implemented FormulaConvertible!");
            }
            if (prop.getKey() != getKey() && prop instanceof MolarMassConvertible) {
                throw new IllegalStateException(
                        "Material: ${material.key} cannot have two or more properties implemented MolarMassConvertible!");
            }
        });
    }
}
