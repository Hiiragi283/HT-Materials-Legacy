package io.github.hiiragi283.htms.api.material.property;

import io.github.hiiragi283.htms.api.extension.TypedResourceLocation;
import io.github.hiiragi283.htms.api.material.HTMaterial;

public interface HTMaterialProperty<T extends HTMaterialProperty<T>> {

    TypedResourceLocation<T> getId();

    default void addInformation(HTMaterial.TooltipContext context) {}
}
