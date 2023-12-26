package io.github.hiiragi283.material.api.material.property;

import io.github.hiiragi283.material.api.material.HTMaterial;

public interface HTMaterialProperty<T extends HTMaterialProperty<T>> {

    HTPropertyKey<T> getKey();


    void verify(HTMaterial material);

}