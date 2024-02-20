package io.github.hiiragi283.api;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import io.github.hiiragi283.api.material.HTMaterialKey;
import io.github.hiiragi283.api.material.composition.HTMaterialComposition;
import io.github.hiiragi283.api.material.flag.HTMaterialFlagSet;
import io.github.hiiragi283.api.material.property.HTMaterialPropertyMap;
import io.github.hiiragi283.api.part.HTPartManager;
import io.github.hiiragi283.api.shape.HTShapeKey;

public interface HTMaterialsAddon {

    String getModId();

    int getPriority();

    // PreInit

    default void registerShapeKey(ImmutableSet.Builder<HTShapeKey> builder) {}

    default void registerMaterialKey(ImmutableMap.Builder<HTMaterialKey, Integer> builder) {}

    default void modifyMaterialComposition(Map<HTMaterialKey, HTMaterialComposition> map) {}

    default void modifyMaterialFlag(Map<HTMaterialKey, HTMaterialFlagSet.Builder> map) {}

    default void modifyMaterialProperty(Map<HTMaterialKey, HTMaterialPropertyMap.Builder> map) {}

    // Init

    default void bindItemToPart(HTPartManager.Builder builder) {}
}
