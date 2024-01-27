package io.github.hiiragi283.material.api.material.property.component;

import io.github.hiiragi283.material.api.material.ColorConvertible;
import io.github.hiiragi283.material.api.material.FormulaConvertible;
import io.github.hiiragi283.material.api.material.HTMaterial;
import io.github.hiiragi283.material.api.material.MolarMassConvertible;
import io.github.hiiragi283.material.api.material.property.HTMaterialProperty;

public abstract class HTComponentPropertyBase<T extends HTComponentPropertyBase<T>>
                                             implements HTMaterialProperty<T>, ColorConvertible,
                                             FormulaConvertible, MolarMassConvertible {

    @Override
    public void verify(HTMaterial material) {
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
