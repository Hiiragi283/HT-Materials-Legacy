package io.github.hiiragi283.api;

import io.github.hiiragi283.api.material.HTMaterialHelper;
import io.github.hiiragi283.api.part.HTPartManager;
import io.github.hiiragi283.api.shape.HTShapeHelper;

public interface HTMaterialsAddon {

    String getModId();

    int getPriority();

    // PreInit
    default void registerShape(HTShapeHelper shapeHelper) {}

    default void registerMaterial(HTMaterialHelper materialHelper) {}

    // Init

    default void bindItemToPart(HTPartManager.Builder builder) {}
}
